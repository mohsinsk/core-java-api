package com.core.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Database Credentials
    private static final String URL = "jdbc:mysql://host.docker.internal:3306/core_java_api";
    private static final String USER = "root";
    private static final String PASSWORD = "Mysql@1mohsinsk";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

