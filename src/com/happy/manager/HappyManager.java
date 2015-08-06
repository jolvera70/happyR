package com.happy.manager;

import com.happy.bean.EventHappy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jolvera on 15/07/2015.
 */
public final class HappyManager {

    private final EventHappy eventHappy;
    private final String name;

    private HappyManager(final String name, final EventHappy eventHappy) {
        this.eventHappy = eventHappy;
        this.name = name;
    }

    public static HappyManager createNewInstance(final String name, final EventHappy eventHappy) {
        return new HappyManager(name, eventHappy);
    }

    private static final Map<String, List<EventHappy>> map = new HashMap<String, List<EventHappy>>();

    protected static Map<String, List<EventHappy>> showAllClients() {
        return map;
    }

    protected static List<EventHappy> showAllEventsByName(final String name) {
        return map.get(name);
    }

    protected String addEvent() {
        try {
            addEventToCache(name, eventHappy);
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return map.get(name) + "- Agregado";
    }

    protected String addClient() {
        try {
            addClientToCache(name, eventHappy);
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return name + "- Agregado";
    }

    protected String deleteEvent() {
        try {
            if (map.containsKey(name)) {
                deleteEventToCache(name, eventHappy);
            } else {
                return name + " NOT found in cache";
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return name + " - Eliminado";
    }

    protected String deleteClient() {
        try {
            if (map.containsKey(name)) {
                deleteClientToCache(name, eventHappy);
            } else {
                return name + " NOT found in cache";
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return name + " - Eliminado";
    }

    private static void addEventToCache(final String name, final EventHappy eventHappy) {
        final List<EventHappy> events;
        if (map.get(name) != null) {
            events = map.get(name);
        } else {
            events = new ArrayList<EventHappy>();
        }
        events.add(eventHappy);
        map.put(name, events);
    }

    private static void addClientToCache(final String name, final EventHappy eventHappy) {
        map.put(name, null);
    }

    private static void deleteEventToCache(final String name, final EventHappy eventHappy) {
        final List<EventHappy> eventsList = map.get(name);
        for (final EventHappy eventHappyResponse : eventsList) {
            if (eventHappyResponse.getId_e() == eventHappy.getId_e()) {
                eventsList.remove(eventHappyResponse);
                map.put(name, eventsList);
            }
        }
    }

    private static void deleteClientToCache(final String name, final EventHappy eventHappy) {
        map.remove(name);
    }
}
