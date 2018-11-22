package com.techinherit.yourpackage.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techinherit.yourpackage.enums.UserRole;
import com.techinherit.yourpackage.enums.UserStatus;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;

    @Column(name = "last_password_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordDate;

    @Column(name = "password")
    private String password;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Size(max = 200)
    @Column(name = "username")
    private String username;

    @Basic(optional = false)
    @NotNull
    @Column(name = "verification_code")
    private int verificationCode;

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

    @Size(max = 500)
    @Column(name = "firebase_token")
    @JsonIgnore
    private String firebaseToken;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "users")
    @JsonIgnore
    private UserDetails userDetails;

}
