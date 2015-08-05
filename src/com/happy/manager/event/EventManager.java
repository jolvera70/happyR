package com.happy.manager.event;

import com.happy.manager.event.bean.EventHappy;
import com.happy.service.utility.event.EventUtility;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.io.StringWriter;
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
    public String addEvent(@Context UriInfo info) {
        final EventHappy eventHappy = new EventHappy.Builder(info.getQueryParameters().getFirst("type"), info.getQueryParameters().getFirst("comment")).image_e(info.getQueryParameters().getFirst("image")).build();
        return EventUtility.add(info.getQueryParameters().getFirst("name"), eventHappy);
    }

    @GET
    @Path("/delete")
    @Produces("text/plain")
    public String deleteEvent(@Context UriInfo info) {
        final EventHappy eventHappy = new EventHappy.Builder(info.getQueryParameters().getFirst("type"), info.getQueryParameters().getFirst("comment")).build();
        return EventUtility.delete(info.getQueryParameters().getFirst("name"), eventHappy);
    }

    @GET
    @Path("/view/client")
    @Produces("application/json")
    public String viewEvents(@Context UriInfo info) {
        List<EventHappy> eventHappyList = EventUtility.showAllEventsByName(info.getQueryParameters().getFirst("name"));
        if(eventHappyList==null) {return emptyResponse();}
        JSONArray ja = new JSONArray();
        JSONObject mainObj = new JSONObject();
        for (EventHappy eventHappy : eventHappyList) {
            JSONObject myObject = new JSONObject();
            try {
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

    private String emptyResponse() {
        JSONArray ja = new JSONArray();
        JSONObject mainObj = new JSONObject();
            JSONObject myObject = new JSONObject();
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
        //return ja.toString();
        return "{\"error\":\"no exist events\"}";
    }
}
