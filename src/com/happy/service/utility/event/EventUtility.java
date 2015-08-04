package com.happy.service.utility.event;

/**
 * Created by jolvera on 15/07/2015.
 */
public final class EventUtility {

    private EventUtility(){}

    public static final String add(final String type,final String name){
        EventAction eventAction = EventAction.createNewInstance(type,name);
        return eventAction.addEvent();
    }

    public static final String delete(final String type,final String name){
        EventAction eventAction = EventAction.createNewInstance(type,name);
        return eventAction.deleteEvent();
    }

    public static final String showAllEvents(){
        return EventAction.showAllEvents();
    }
}
