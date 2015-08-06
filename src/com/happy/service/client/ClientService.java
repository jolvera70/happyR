package com.happy.service.client;

import com.happy.manager.ClientManager;
import com.happy.bean.EventHappy;
import com.happy.manager.utility.UtilityManager;
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
 * Created by Jorge Olvera on 05/08/2015.
 */
@Path("/happy/client")
public class ClientService {

    @GET
    @Path("/add")
    @Produces("text/plain")
    public String addEvent(final @Context UriInfo info) {
        final EventHappy eventHappy;
        if (info.getQueryParameters().getFirst("name").trim().length() <= 0) {
            return UtilityManager.returnError("El nombre no puede ser vacio");
        }
        return ClientManager.add(info.getQueryParameters().getFirst("name"), null);
    }

    @GET
    @Path("/delete")
    @Produces("text/plain")
    public String deleteEvent(final @Context UriInfo info) {
        final EventHappy eventHappy;
        try {
            if (info.getQueryParameters().getFirst("name").trim().length() <= 0) {
                return UtilityManager.returnError("El nombre no puede ser vacio");
            }
            eventHappy = new EventHappy.Builder(0, info.getQueryParameters().getFirst("type"), info.getQueryParameters().getFirst("comment")).build();
        } catch (Exception e) {
            System.out.println("Error al inicializar el cliente para borrar: " + e);
            return UtilityManager.returnError("Error al inicializar el cliente para borrar");
        }
        return ClientManager.delete(info.getQueryParameters().getFirst("name"), eventHappy);
    }

    @GET
    @Path("/view")
    @Produces("application/json")
    public String viewAllClients(final @Context UriInfo info) {
        final Map<String, List<EventHappy>> clientsList = ClientManager.showAllClients();
        if (clientsList.size() <= 0) {
            return clientEmptyResponse();
        }
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
}
