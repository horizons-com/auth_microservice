package com.horizons.authmicroservice.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class AppUser {
    @Id
    private ObjectId id;
    private String userName;
    private String password;

    public String getId() {
        return id.toHexString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
