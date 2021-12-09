package com.example.subprojectma14ctttantritoanlenhuyn5.model;

import java.util.Set;

public class UserCreate {

    private String username;

    private String password;

    private Set<String> roles;

    public UserCreate(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
