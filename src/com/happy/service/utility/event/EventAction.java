package com.happy.service.utility.event;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jolvera on 15/07/2015.
 */
public final class EventAction {

    private final String name;

    private EventAction(final String name) {
        this.name = name;
    }

    public static EventAction createNewInstance(String name){
        return new EventAction(name);
    }

    private static final Map<String, String> map = new HashMap<String, String>();

    protected static void showAllEvents() {
        System.out.println(map);
    }

    protected String addEvent() {
        try {
            System.out.println("encrypting: " + name);
            encryptionValidate(name);
            if (map.containsKey(name)) {
                System.out.println(name + " found in cache");
            } else {
                final String encryptName = encryptingDecrypting(true);
                addNameToCache(name, encryptName);
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return map.get(name);
    }

    protected void deleteEvent() {
        try {
            System.out.println("decrypting: " + name);
            decryptionValidate(name);
            final String name = encryptingDecrypting(false);
            if (map.containsValue(name)) {
                System.out.println(name + " found in cache");
            } else {
                addNameToCache(name, name);
            }
            System.out.println("result= " + name);
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error " + e);

        }
    }
    private static void addNameToCache(final String name, final String encryptName) {
        System.out.println("adding to cache");
        map.put(name, encryptName);
    }

    private static void decryptionValidate(final String decryptName) {
        if (decryptName.length() % 2 > 0) {
            throw new IllegalArgumentException("Invalid encrypted value, the length must be a par number :" + decryptName);
        }
        if (decryptName.replaceFirst("#", "").indexOf("#") != -1) {
            throw new IllegalArgumentException("Invalid encrypted value,is not valid send more than one character '#':" + decryptName);
        }
        if (decryptName.indexOf(" ") != -1) {
            throw new IllegalArgumentException("Invalid encrypted value,is not valid send ' ' space character:" + decryptName);
        }
    }


    private static void encryptionValidate(final String name) {
        if (name.indexOf("#") != -1) {
            throw new IllegalArgumentException("Invalid decrypted value, invalid character '#':" + name);
        }
        if (name.indexOf(" ") != -1) {
            throw new IllegalArgumentException("Invalid decrypted value,invalid space character ' ':" + name);
        }
    }

    /**
     *
     * @param encrypting: true in order to encrypt the value, or false de decrypt the value.
     * @return String with the value encrypt or decrypt, depends of encrypting param value
     */
    private String encryptingDecrypting(final boolean encrypting) {
        final StringBuilder stringBuilder = new StringBuilder();
        try {
            if (name.length() % 2 > 0) {
                stringBuilder.append(name.substring((name.length() / 2) + 1));
                if (encrypting) {
                    stringBuilder.append("#");
                }
                stringBuilder.append(name.substring(0, (name.length() / 2) + 1));
            } else {
                stringBuilder.append(name.substring(name.length() / 2));
                stringBuilder.append(name.substring(0, name.length() / 2));
            }
        } catch (Exception e) {
            System.out.println("Error in encryptingDecrypting " + name + ":" + e);
        }
        if (encrypting) {
            return String.valueOf(stringBuilder);
        } else {
            return String.valueOf(stringBuilder).replace("#", "");
        }
    }
}
