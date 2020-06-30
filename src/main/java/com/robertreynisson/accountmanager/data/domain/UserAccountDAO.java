package com.robertreynisson.accountmanager.data.domain;

import com.robertreynisson.accountmanager.controllers.domain.UserAccountAccountCreate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class UserAccountDAO {

    @Id
    @GenericGenerator(name = "user_account_id", strategy = "increment")
    @GeneratedValue(generator = "user_account_id")
    private Long id;
    private String firstName;
    private String lastName;
    @Column(name = "USERNAME", unique = true)
    private String userName;
    private String password;
    private String email;
    private String phone;
    private String role;


    public UserAccountDAO() {
    }

    public UserAccountDAO(UserAccountAccountCreate userAccountCreate) {
        this.firstName = userAccountCreate.getFirstName();
        this.lastName = userAccountCreate.getLastName();
        this.userName = userAccountCreate.getUserName();
        this.email = userAccountCreate.getEmail();
        this.password = userAccountCreate.getPassword();
        this.phone = userAccountCreate.getPhone();
        this.role = String.valueOf(userAccountCreate.getRole());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
