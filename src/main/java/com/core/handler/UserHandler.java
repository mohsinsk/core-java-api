package com.core.handler;

import com.core.model.User;
import com.core.repository.UserRepository;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

public class UserHandler implements HttpHandler {
    private final Gson gson = new Gson();
    private final UserRepository userRepository = new UserRepository(); // Instantiate Repository

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        int userId = getUserIdFromPath(path);

        try {
            if ("GET".equals(method)) {
                if (userId != -1) getOne(exchange, userId);
                else getAll(exchange);
            }
            else if ("POST".equals(method)) create(exchange);
            else if ("PUT".equals(method) && userId != -1) update(exchange, userId);
            else if ("DELETE".equals(method) && userId != -1) delete(exchange, userId);
            else sendResponse(exchange, "Method Not Allowed", 405);

        } catch (SQLException e) {
            e.printStackTrace();
            sendResponse(exchange, "Internal Server Error", 500);
        }
    }

    private void getAll(HttpExchange exchange) throws IOException, SQLException {
        List<User> users = userRepository.findAll();
        sendResponse(exchange, gson.toJson(users), 200);
    }

    private void getOne(HttpExchange exchange, int id) throws IOException, SQLException {
        User user = userRepository.findById(id);
        if (user != null) sendResponse(exchange, gson.toJson(user), 200);
        else sendResponse(exchange, "User not found", 404);
    }

    private void create(HttpExchange exchange) throws IOException, SQLException {
        User newUser = gson.fromJson(readBody(exchange), User.class);
        userRepository.save(newUser);
        sendResponse(exchange, "User Created", 201);
    }

    private void update(HttpExchange exchange, int id) throws IOException, SQLException {
        User u = gson.fromJson(readBody(exchange), User.class);
        if (userRepository.update(id, u)) sendResponse(exchange, "User Updated", 200);
        else sendResponse(exchange, "User Not Found", 404);
    }

    private void delete(HttpExchange exchange, int id) throws IOException, SQLException {
        if (userRepository.delete(id)) sendResponse(exchange, "User Deleted", 200);
        else sendResponse(exchange, "User Not Found", 404);
    }

    // --- Utilities ---
    private String readBody(HttpExchange exchange) throws IOException {
        return new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
    }

    private void sendResponse(HttpExchange exchange, String response, int code) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(code, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) { os.write(bytes); }
    }

    private int getUserIdFromPath(String path) {
        String[] parts = path.split("/");
        return (parts.length == 4) ? Integer.parseInt(parts[3]) : -1;
    }
}

