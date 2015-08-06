package com.happy.service.utility.client;

import com.happy.service.utility.event.EventAction;

import java.util.List;
import java.util.Map;

/**
 * Created by Jorge Olvera on 05/08/2015.
 */
public final class ClientUtility {

    private ClientUtility(){}

    public static final String add(final String name){
        final EventAction eventAction = EventAction.createNewInstance(name);
        return eventAction.addEvent();
    }

    public static final String delete(final String name,final EventHappy eventHappy){
        final EventAction eventAction = EventAction.createNewInstance(name,eventHappy);
        return eventAction.deleteEvent();
    }

    public static final Map<String, List<EventHappy>> showAllClients(){
        return EventAction.showAllClients();
    }
}
