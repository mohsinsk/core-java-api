package com.core.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    // Get application mode (docker or local)
    public static String getMode() {
        return getEnv("MODE", "local");
    }

    public static String getDbUrl() {
        String mode = getMode();
        String host, dbName;
        int port;

        if ("docker".equalsIgnoreCase(mode)) {
            host = getEnv("DB_HOST_DOCKER", "mysql");
        } else {
            host = getEnv("DB_HOST_LOCAL", "127.0.0.1");
        }

        port = Integer.parseInt(getEnv("DB_PORT", "3306"));
        dbName = getDbName();

        return String.format("jdbc:mysql://%s:%d/%s", host, port, dbName);
    }

    public static String getDbUser() {
        return getEnv("DB_USER", "root");
    }

    public static String getDbPassword() {
        return getEnv("DB_PASSWORD", "");
    }

    public static String getDbName() {
        return getEnv("DB_NAME", "core_java_api");
    }

    public static String getAuthToken() {
        return getEnv("AUTH_TOKEN", "");
    }

    private static String getEnv(String key, String defaultValue) {
        String value = dotenv.get(key);
        return value != null ? value : System.getenv(key) != null ? System.getenv(key) : defaultValue;
    }
}

