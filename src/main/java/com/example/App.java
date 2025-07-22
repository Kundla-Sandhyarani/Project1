package com.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.*;

public class App {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", exchange -> {
            String response = "Hello from CI/CD PoC Web App!";
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.getResponseBody().close();
        });
        server.setExecutor(null); // Uses default thread pool
        server.start();
        System.out.println("Server started at http://localhost:8080");
    }

    public int add(int a, int b) {
        return a + b;
    }
}
