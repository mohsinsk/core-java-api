package com.core.filter;

import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;

public class AuthFilter extends Filter {

    @Override
    public String description() {
        return "Checks for Authorization Header";
    }

    @Override
    public void doFilter(HttpExchange exchange, Chain chain) throws IOException {
        // 1. Check for Header
        if (exchange.getRequestHeaders().containsKey("Authorization")) {
            String token = exchange.getRequestHeaders().getFirst("Authorization");

            // 2. Validate Token (Simple static check for now)
            // In a real app, you would query the DB or validate a JWT here.
            if ("Bearer my-secret-token-123".equals(token)) {
                chain.doFilter(exchange); // Pass to the next handler
                return;
            }
        }

        // 3. Reject if invalid
        String response = "401 Unauthorized - Missing or Invalid Token";
        exchange.sendResponseHeaders(401, response.length());
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseBody().close();
    }
}

