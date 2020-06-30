package com.robertreynisson.accountmanager.controllers.domain;

import com.robertreynisson.accountmanager.data.domain.UserAccountDAO;

public class UserAccount {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Role role;

    public UserAccount() {
    }

    public UserAccount(UserAccountDAO userAccountDAO) {
        this.id = userAccountDAO.getId();
        this.userName = userAccountDAO.getUserName();
        this.firstName = userAccountDAO.getFirstName();
        this.lastName = userAccountDAO.getLastName();
        this.email = userAccountDAO.getEmail();
        this.phone = userAccountDAO.getPhone();
        this.role = Role.valueOf(userAccountDAO.getRole());
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
