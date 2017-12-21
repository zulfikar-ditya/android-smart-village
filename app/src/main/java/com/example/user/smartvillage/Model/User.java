package com.example.user.smartvillage.Model;

/**
 * Created by user on 03/12/2017.
 */

public class User {
    private String username, token, role;
    private int id;

    public User(String username, String token, String role, int id) {
        this.username = username;
        this.token = token;
        this.role = role;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
