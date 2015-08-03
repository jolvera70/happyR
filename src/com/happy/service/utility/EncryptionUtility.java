package com.happy.service.utility;

/**
 * Created by jolvera on 15/07/2015.
 */
public final class EncryptionUtility{

    private EncryptionUtility(){}

    public static final String encryption(final String name){
        ListNameCache listNameCache = ListNameCache.createNewInstance(name);
        return listNameCache.encryptingName();
    }

    public static final void decryption(final String name){
        ListNameCache listNameCache = ListNameCache.createNewInstance(name);
        listNameCache.decryptingName();
    }

    public static final void showAllNames(){
        ListNameCache.showAllNames();
    }
}
