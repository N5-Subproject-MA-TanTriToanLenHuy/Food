package com.example.subprojectma14ctttantritoanlenhuyn5.model;

public class User {

    private String username;

    private String password;

    private String token;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }
}
