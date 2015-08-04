package com.happy.service;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;

/**
 * Created by Jorge Olvera on 03/08/2015.
 */
public class HelloWorldClient {
  public static void main(String[] argv) throws IOException {
      HttpServer server = HttpServerFactory.create("http://localhost:9999/");
      server.start();

      System.out.println("Server running");
      System.out.println("Visit: http://localhost:9998/event");
      System.out.println("Hit return to stop...");
      System.in.read();
      System.out.println("Stopping server");
      server.stop(0);
      System.out.println("Server stopped");
  }
}
