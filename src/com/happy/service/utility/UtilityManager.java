package com.happy.service.utility;

/**
 * Created by Jorge Olvera on 05/08/2015.
 */
public class UtilityManager {

    public static String returnError(final String error) {
        return "{\"error\":\""+error+"\"}";
    }
}
