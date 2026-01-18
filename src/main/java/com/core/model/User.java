package com.core.model;

public class User {
    private int id;
    private String name;
    private String role;

    // Constructors
    public User(int id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    // Getters and Setters (Important for Gson to work)
    public int getId() { return id; }
    public String getName() { return name; }
    public String getRole() { return role; }
}


