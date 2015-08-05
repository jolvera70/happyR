package com.happy.manager.event;

import com.happy.manager.event.bean.EventHappy;
import com.happy.service.utility.event.EventUtility;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Map;

/**
 * Created by Jorge Olvera on 03/08/2015.
 */
// The Java class will be hosted at the URI path "/event"
@Path("/event")
public class EventManager {

    @GET
    @Path("/add")
    @Produces("text/plain")
    public String addEvent(final @Context UriInfo info) {
        final EventHappy eventHappy = new EventHappy.Builder(Integer.parseInt(info.getQueryParameters().getFirst("id")),info.getQueryParameters().getFirst("type"), info.getQueryParameters().getFirst("comment")).image_e(info.getQueryParameters().getFirst("image")).build();
        return EventUtility.add(info.getQueryParameters().getFirst("name"), eventHappy);
    }

    @GET
    @Path("/delete")
    @Produces("text/plain")
    public String deleteEvent(final @Context UriInfo info) {
        final EventHappy eventHappy;
        try {
            eventHappy = new EventHappy.Builder(Integer.parseInt(info.getQueryParameters().getFirst("id")), info.getQueryParameters().getFirst("type"), info.getQueryParameters().getFirst("comment")).build();
        }catch (Exception e){
            System.out.println("Error al inicializar el evento para borrar: "+e);
            return returnError("Error al inicializar el evento para borrar");
        }
        return EventUtility.delete(info.getQueryParameters().getFirst("name"), eventHappy);
    }

    @GET
    @Path("/view/client")
    @Produces("application/json")
    public String viewEvents(final @Context UriInfo info) {
        final List<EventHappy> eventHappyList = EventUtility.showAllEventsByName(info.getQueryParameters().getFirst("name"));
        if(eventHappyList==null) {return eventEmptyResponse();}
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
    @Path("/view/clients")
    @Produces("application/json")
    public String viewAllClients(final @Context UriInfo info) {
        final Map<String, List<EventHappy>> clientsList = EventUtility.showAllClients();
        if(clientsList.size()<=0) {return clientEmptyResponse();}
        final JSONArray ja = new JSONArray();
        final JSONObject mainObj = new JSONObject();
        for (final Map.Entry<String, List<EventHappy>> eventHappy : clientsList.entrySet()) {
            final JSONObject myObject = new JSONObject();
            try {
                myObject.put("name", eventHappy.getKey());
            } catch (Exception ex) {
                System.out.println("--> Error ..." + ex);
            }
            ja.put(myObject);
            try {
                mainObj.put("client", ja);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mainObj.toString();
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

    private String clientEmptyResponse() {
        final JSONArray ja = new JSONArray();
        final JSONObject mainObj = new JSONObject();
        final JSONObject myObject = new JSONObject();
        try {
            myObject.put("error", "no exist client");
        } catch (Exception ex) {
            System.out.println("--> Error ..." + ex);
        }
        ja.put(myObject);
        try {
            mainObj.put("client", ja);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mainObj.toString();
    }

    private String returnError(final String error) {
        return "{\"error\":\""+error+"\"}";
    }
}
