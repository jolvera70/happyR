package com.happy.service.utility.event;

import com.happy.manager.event.bean.EventHappy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jolvera on 15/07/2015.
 */
public final class EventAction {

    private final EventHappy eventHappy;
    private final String name;

    private EventAction(final String name, final EventHappy eventHappy) {
        this.eventHappy = eventHappy;
        this.name = name;
    }

    public static EventAction createNewInstance(final String name, final EventHappy eventHappy) {
        return new EventAction(name, eventHappy);
    }

    private static final Map<String, List<EventHappy>> map = new HashMap<String, List<EventHappy>>();

    protected static Map<String, List<EventHappy>> showAllEvents() {
        return map;
    }

    protected static List<EventHappy> showAllEventsByName(final String name) {
        return map.get(name);
    }

    protected String addEvent() {
        try {
            addNameToCache(name, eventHappy);
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return map.get(name) + "- Agregado";
    }

    protected String deleteEvent() {
        try {
            if (map.containsKey(name)) {
                deleteNameToCache(name);
            } else {
                return name + " NOT found in cache";
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return name + " - Eliminado";
    }

    private static void addNameToCache(final String name, final EventHappy eventHappy) {
        List<EventHappy> events;
        if (map.get(name) != null) {
            events = map.get(name);
        } else {
            events = new ArrayList<EventHappy>();
        }
        events.add(eventHappy);
        map.put(name, events);
    }

    private static void deleteNameToCache(final String name) {
        map.remove(name);
    }
}
