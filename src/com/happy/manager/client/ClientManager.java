package com.happy.manager.client;

import com.happy.manager.event.bean.EventHappy;
import com.happy.service.utility.UtilityManager;
import com.happy.service.utility.client.ClientUtility;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * Created by Jorge Olvera on 05/08/2015.
 */
@Path("/client")
public class ClientManager {

    @GET
    @Path("/add")
    @Produces("text/plain")
    public String addEvent(final @Context UriInfo info) {
        final EventHappy eventHappy;
        try {
            if(info.getQueryParameters().getFirst("name").trim().length()<=0){
                return UtilityManager.returnError("El nombre no puede ser vacio");
            }
            eventHappy = new EventHappy.Builder(Integer.parseInt(info.getQueryParameters().getFirst("id")),info.getQueryParameters().getFirst("type"), info.getQueryParameters().getFirst("comment")).image_e(info.getQueryParameters().getFirst("image")).build();
        }catch (Exception e){
            System.out.println("Error al inicializar el evento para agregar: "+e);
            return UtilityManager.returnError("Error al inicializar el evento para agregar");
        }
        return ClientUtility
                .add(info.getQueryParameters().getFirst("name"), eventHappy);
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
            return UtilityManager.returnError("Error al inicializar el evento para borrar");
        }
        return ClientUtility.delete(info.getQueryParameters().getFirst("name"), eventHappy);
    }
}
