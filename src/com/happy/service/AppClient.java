package com.happy.service;

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

        String urlParameters = "name=Luis&type=f&comment=tristeL";
        URL url = new URL("http://localhost:9998/event/add");
        URLConnection conn = url.openConnection();

        conn.setDoOutput(true);

        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

        writer.write(urlParameters);
        writer.flush();


        writer.close();
    }

}
