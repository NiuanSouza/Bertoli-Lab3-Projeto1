package com.thehecklers.userrestdemo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "users")
public class Users {

    @Id
    private String id;
    private String name;
    private String email;

    public Users() {
    }

    public Users(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Users(String name, String email) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}