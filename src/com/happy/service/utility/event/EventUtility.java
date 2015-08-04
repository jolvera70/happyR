package com.happy.service.utility.event;

import com.happy.manager.event.bean.EventHappy;

import java.util.List;
import java.util.Map;

/**
 * Created by jolvera on 15/07/2015.
 */
public final class EventUtility {

    private EventUtility(){}

    public static final String add(final String name,final EventHappy eventHappy){
        EventAction eventAction = EventAction.createNewInstance(name,eventHappy);
        return eventAction.addEvent();
    }

    public static final String delete(final String name,final EventHappy eventHappy){
        EventAction eventAction = EventAction.createNewInstance(name,eventHappy);
        return eventAction.deleteEvent();
    }

    public static final Map<String, List<EventHappy>> showAllEvents(){
        return EventAction.showAllEvents();
    }

    public static final List<EventHappy> showAllEventsByName(final String name){
        return EventAction.showAllEventsByName(name);
    }
}
