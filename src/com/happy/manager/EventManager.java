package com.happy.manager;

import com.happy.bean.EventHappy;

import java.util.List;

/**
 * Created by jolvera on 15/07/2015.
 */
public final class EventManager {

    private EventManager(){}

    public static final String add(final String name,final EventHappy eventHappy){
        final HappyManager happyManager = HappyManager.createNewInstance(name, eventHappy);
        return happyManager.addEvent();
    }

    public static final String delete(final String name,final EventHappy eventHappy){
        final HappyManager happyManager = HappyManager.createNewInstance(name, eventHappy);
        return happyManager.deleteEvent();
    }

    public static final List<EventHappy> showAllEventsByName(final String name){
        return HappyManager.showAllEventsByName(name);
    }
}
