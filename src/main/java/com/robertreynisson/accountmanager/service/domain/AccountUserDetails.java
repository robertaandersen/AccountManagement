package com.robertreynisson.accountmanager.service.domain;

import com.robertreynisson.accountmanager.data.domain.UserAccountDAO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class AccountUserDetails implements UserDetails {


    private String role;
    private String password;
    private String userName;

    public AccountUserDetails() {
    }

    public AccountUserDetails(UserAccountDAO userAccountDAO) {
        this.role = userAccountDAO.getRole();
        this.password = userAccountDAO.getPassword();
        this.userName = userAccountDAO.getUserName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    /**
     * Longterm lifecycle of user management is out of scope.
     * For our intents and purposes we'll keep those properties
     * fixed at default values...
     **/
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
