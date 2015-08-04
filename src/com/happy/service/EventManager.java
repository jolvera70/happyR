package com.happy.service;
import com.happy.service.utility.EncryptionUtility;
import com.happy.service.utility.event.EventUtility;
import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

/**
 * Created by Jorge Olvera on 03/08/2015.
 */
// The Java class will be hosted at the URI path "/encryption"
@Path("/event")
public class EventManager {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public void getEncryption() {
        // Return some cliched textual content
        EventUtility.add("Sridhar");
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServerFactory.create("http://localhost:9998/");
        server.start();

        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9998/encryption");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }
}
