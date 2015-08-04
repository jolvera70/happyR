package com.happy.service.utility.event;

import com.happy.service.utility.ListNameCache;

/**
 * Created by jolvera on 15/07/2015.
 */
public final class EventUtility {

    private EventUtility(){}

    public static final void add(final String name){
        EventAction eventAction = EventAction.createNewInstance(name);
        eventAction.addEvent();
    }

    public static final void delete(final String name){
        EventAction eventAction = EventAction.createNewInstance(name);
        eventAction.deleteEvent();
    }

    public static final void showAllEvents(){
        EventAction.showAllEvents();
    }
}
