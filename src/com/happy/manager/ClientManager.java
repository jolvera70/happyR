package com.happy.manager;

import com.happy.bean.EventHappy;

import java.util.List;
import java.util.Map;

/**
 * Created by Jorge Olvera on 05/08/2015.
 */
public final class ClientManager {

    private ClientManager(){}

    public static final String add(final String name,final EventHappy eventHappy){
        final HappyManager happyManager = HappyManager.createNewInstance(name, eventHappy);
        return happyManager.addClient();
    }

    public static final String delete(final String name,final EventHappy eventHappy){
        final HappyManager happyManager = HappyManager.createNewInstance(name, eventHappy);
        return happyManager.deleteClient();
    }

    public static final Map<String, List<EventHappy>> showAllClients(){
        return HappyManager.showAllClients();
    }
}
