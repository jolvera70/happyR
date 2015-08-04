package com.happy.service.event;

import com.happy.service.utility.event.EventUtility;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

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
        return EventUtility.add(info.getQueryParameters().getFirst("name"));
    }

    @GET
    @Path("/delete")
    @Produces("text/plain")
    public String deleteEvent(@Context UriInfo info) {
        return EventUtility.delete(info.getQueryParameters().getFirst("name"));
    }

    @GET
    @Path("/view")
    @Produces("text/plain")
    public String viewEvents() {
        return EventUtility.showAllEvents();
    }
}
