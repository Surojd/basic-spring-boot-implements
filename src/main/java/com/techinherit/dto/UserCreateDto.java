package com.techinherit.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techinherit.tracking.enums.UserRole;
import com.techinherit.tracking.enums.UserStatus;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserCreateDto {

    @NotNull
    @NotEmpty
    private String firstName;
    @NotNull
    @NotEmpty
    private String lastName;
    @JsonIgnore
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private UserRole userRole;
    @NotNull
    @NotEmpty
    private UserStatus userStatus;
    @NotNull
    @NotEmpty
    private String username;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
