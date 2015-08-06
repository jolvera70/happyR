package com.happy;

import java.io.*;
import java.net.URL;
/**
 * Created by Jorge Olvera on 03/08/2015.
 */
public class AppClient {
    public static void main(String[] argv) throws IOException {

        String string = "";
        try {

            // Step1: Let's 1st read file from fileSystem
            // Change CrunchifyJSON.txt path here
            final URL oracle = new URL("http://localhost:9998/happy/client/view");
            final BufferedReader br = new BufferedReader(
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
