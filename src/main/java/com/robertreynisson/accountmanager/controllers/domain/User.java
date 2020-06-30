package com.robertreynisson.accountmanager.controllers.domain;

import com.robertreynisson.accountmanager.data.domain.UserDAO;

import java.io.Serializable;

public class User implements Serializable {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Role role;

    public User() {
    }

    public User(UserDAO userDAO) {
        this.id = userDAO.getId();
        this.userName = userDAO.getUserName();
        this.firstName = userDAO.getFirstName();
        this.lastName = userDAO.getLastName();
        this.email = userDAO.getEmail();
        this.phone = userDAO.getPhone();
        this.role = Role.valueOf(userDAO.getRole());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
