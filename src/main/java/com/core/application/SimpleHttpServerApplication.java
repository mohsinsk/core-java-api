package com.core.application;

import com.core.filter.AuthFilter;
import com.core.handler.UserHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SimpleHttpServerApplication {
    // 1. Initialize Logger
    private static final Logger logger = Logger.getLogger(SimpleHttpServerApplication.class.getName());

    public static void main(String[] args) throws IOException {
        configureLogging(); // Helper method to make logs look nice

        int PORT = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

        // Define Endpoints
        var userContext = server.createContext("/api/users", new UserHandler());

        userContext.getFilters().add(new AuthFilter());

        // We will add Authentication here in the next step!

        server.setExecutor(null);
        logger.info("Server started on port " + PORT); // Use logger instead of sysout
        server.start();
    }

    private static void configureLogging() {
        // Remove default handlers to customize format
        Logger rootLogger = Logger.getLogger("");
        for (var handler : rootLogger.getHandlers()) {
            rootLogger.removeHandler(handler);
        }

        // Create a console handler with a simple clean format
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter() {
            private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";
            @Override
            public synchronized String format(java.util.logging.LogRecord lr) {
                return String.format(format,
                        new java.util.Date(lr.getMillis()),
                        lr.getLevel().getLocalizedName(),
                        lr.getMessage()
                );
            }
        });
        handler.setLevel(Level.ALL);
        rootLogger.addHandler(handler);
        rootLogger.setLevel(Level.INFO);
    }
}

