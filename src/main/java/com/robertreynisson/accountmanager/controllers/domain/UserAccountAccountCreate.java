package com.robertreynisson.accountmanager.controllers.domain;

/**
 * Make sure password is never passed down
 * in a readable format
 **/
public class UserAccountAccountCreate extends UserAccount {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
