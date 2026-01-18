package com.core.repository;

import com.core.config.EnvConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                EnvConfig.getDbUrl(),
                EnvConfig.getDbUser(),
                EnvConfig.getDbPassword()
        );
    }
}

