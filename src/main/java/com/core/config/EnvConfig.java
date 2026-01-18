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
        if ("docker".equalsIgnoreCase(mode)) {
            return getEnv("DB_URL_DOCKER", "jdbc:mysql://mysql:3306/core_java_api");
        } else {
            return getEnv("DB_URL_LOCAL", "jdbc:mysql://127.0.0.1:3306/core_java_api");
        }
    }

    public static String getDbUser() {
        return getEnv("DB_USER", "root");
    }

    public static String getDbPassword() {
        return getEnv("DB_PASSWORD", "");
    }

//    public static String getDbName() {
//        return getEnv("DB_NAME", "core_java_api");
//    }

    public static String getAuthToken() {
        return getEnv("AUTH_TOKEN", "");
    }

    private static String getEnv(String key, String defaultValue) {
        String value = dotenv.get(key);
        return value != null ? value : System.getenv(key) != null ? System.getenv(key) : defaultValue;
    }
}

