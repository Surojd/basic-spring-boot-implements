package com.techinherit.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techinherit.enums.UserRole;
import com.techinherit.enums.UserStatus;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
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

}
