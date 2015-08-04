package com.happy.service;

import org.codehaus.jettison.json.JSONObject;
import org.omg.CORBA.NameValuePair;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jorge Olvera on 03/08/2015.
 */
public class AppClient {
    public static void main(String[] argv) throws IOException {

        String string = "";
        try {

            // Step1: Let's 1st read file from fileSystem
            // Change CrunchifyJSON.txt path here
            URL oracle = new URL("http://localhost:9998/event/view/client");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String line;
            while ((line = br.readLine()) != null) {
                string += line + "\n";
            }
            System.out.println(string);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
