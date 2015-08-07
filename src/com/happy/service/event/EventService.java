package com.happy.service.event;

import com.happy.bean.EventHappy;
import com.happy.manager.utility.ImageUtils;
import com.happy.manager.utility.UtilityManager;
import com.happy.manager.EventManager;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * Created by Jorge Olvera on 03/08/2015.
 */
// The Java class will be hosted at the URI path "/event"
@Path("/happy/event")
public class EventService {

    @GET
    @Path("/add")
    @Produces("text/plain")
    public String addEvent(final @Context UriInfo info) {
        final EventHappy eventHappy;
        String imgstr="";
        String imgName="";
        try {
            if (info.getQueryParameters().getFirst("name").trim().length() <= 0) {
                return UtilityManager.returnError("El nombre no puede ser vacio");
            } else if (info.getQueryParameters().getFirst("type").trim().length() <= 0) {
                return UtilityManager.returnError("El tiipo no puede ser vacio");
            }
            if(info.getQueryParameters().getFirst("imageName").trim().length()>0){
                BufferedImage img = ImageIO.read(new File(info.getQueryParameters().getFirst("imageName").trim()));
                imgstr= ImageUtils.encodeToString(img, "png");;
                imgName=info.getQueryParameters().getFirst("imageName");
            }
            eventHappy = new EventHappy.Builder(Integer.parseInt(info.getQueryParameters().getFirst("id")), info.getQueryParameters().getFirst("type"), info.getQueryParameters().getFirst("comment")).image_e(imgstr).imageName_e(imgName).build();
        } catch (Exception e) {
            System.out.println("Error al inicializar el evento para agregar: " + e);
            return UtilityManager.returnError("Error al inicializar el evento para agregar");
        }
        return EventManager.add(info.getQueryParameters().getFirst("name"), eventHappy);
    }

    @GET
    @Path("/delete")
    @Produces("text/plain")
    public String deleteEvent(final @Context UriInfo info) {
        final EventHappy eventHappy;
        try {
            eventHappy = new EventHappy.Builder(Integer.parseInt(info.getQueryParameters().getFirst("id")), info.getQueryParameters().getFirst("type"), info.getQueryParameters().getFirst("comment")).build();
        } catch (Exception e) {
            System.out.println("Error al inicializar el evento para borrar: " + e);
            return UtilityManager.returnError("Error al inicializar el evento para borrar");
        }
        return EventManager.delete(info.getQueryParameters().getFirst("name"), eventHappy);
    }

    @GET
    @Path("/view")
    @Produces("application/json")
    public String viewEventsByClientName(final @Context UriInfo info) {
        final List<EventHappy> eventHappyList = EventManager.showAllEventsByName(info.getQueryParameters().getFirst("name"));
        if (eventHappyList == null) {
            return eventEmptyResponse();
        }
        final JSONArray ja = new JSONArray();
        final JSONObject mainObj = new JSONObject();
        for (final EventHappy eventHappy : eventHappyList) {
            final JSONObject myObject = new JSONObject();
            try {
                myObject.put("id_e", eventHappy.getId_e());
                myObject.put("type_e", eventHappy.getType_e());
                myObject.put("comment_e", eventHappy.getComment_e());
                myObject.put("image_e", eventHappy.getImage_e());
            } catch (Exception ex) {
                System.out.println("--> Error ..." + ex);
            }
            ja.put(myObject);
            try {
                mainObj.put("event", ja);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mainObj.toString();
    }

    @GET
    @Path("/view/detail")
    @Produces("application/json")
    public String viewEventByClientNameAndId(final @Context UriInfo info) {
        try {
            final EventHappy eventHappy = EventManager.showEventByNameAndId(info.getQueryParameters().getFirst("name"), Integer.parseInt(info.getQueryParameters().getFirst("id")));
            if (eventHappy == null) {
                return eventEmptyResponse();
            }
            final JSONArray ja = new JSONArray();
            final JSONObject mainObj = new JSONObject();
            final JSONObject myObject = new JSONObject();
            myObject.put("id_e", eventHappy.getId_e());
            myObject.put("type_e", eventHappy.getType_e());
            myObject.put("comment_e", eventHappy.getComment_e());
            myObject.put("image_e", eventHappy.getImage_e());

            ja.put(myObject);
            try {
                mainObj.put("event", ja);
            } catch (JSONException e) {
                return UtilityManager.returnError("Error en JSON");
            }
            return mainObj.toString();
        } catch (Exception ex) {
            System.out.println("--> Error ..." + ex);
            return UtilityManager.returnError("--> Error ...");
        }
    }

    @GET
    @Path("/update")
    @Produces("text/plain")
    public String updateEvent(final @Context UriInfo info) {
        final EventHappy eventHappy;
        try {
            eventHappy = new EventHappy.Builder(Integer.parseInt(info.getQueryParameters().getFirst("id")), info.getQueryParameters().getFirst("type"), info.getQueryParameters().getFirst("comment")).image_e(info.getQueryParameters().getFirst("image")).imageName_e(info.getQueryParameters().getFirst("imageName")).build();
        } catch (Exception e) {
            System.out.println("Error al inicializar el evento para actualizar: " + e);
            return UtilityManager.returnError("Error al inicializar el evento para actualizar");
        }
        return EventManager.update(info.getQueryParameters().getFirst("name"), eventHappy);
    }

    private String eventEmptyResponse() {
        final JSONArray ja = new JSONArray();
        final JSONObject mainObj = new JSONObject();
        final JSONObject myObject = new JSONObject();
        try {
            myObject.put("error", "no exist events");
        } catch (Exception ex) {
            System.out.println("--> Error ..." + ex);
        }
        ja.put(myObject);
        try {
            mainObj.put("event", ja);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mainObj.toString();
    }


}
