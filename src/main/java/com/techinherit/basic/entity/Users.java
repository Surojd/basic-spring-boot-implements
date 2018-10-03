package com.techinherit.basic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techinherit.basic.enums.UserRole;
import com.techinherit.basic.enums.UserStatus;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_login_date")
    @Temporal(TemporalType.DATE)
    @JsonIgnore
    private Date lastLoginDate;
    @Size(max = 255)
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "last_password_reset_date")
    @Temporal(TemporalType.DATE)
    @JsonIgnore
    private Date lastPasswordResetDate;
    @Size(max = 255)
    @Column(name = "password")
    @JsonIgnore
    private String password;
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
    @Size(max = 255)
    @Column(name = "username", unique = true)
    private String username;
}
