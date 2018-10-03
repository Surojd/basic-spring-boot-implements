package com.techinherit.basic.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Temporal;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor
@Data
public class OnlineUser implements UserDetails {

    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    @JsonIgnore
    private Collection<? extends GrantedAuthority> authorities;
    private boolean enabled;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastPasswordResetDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastLoginDate;

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
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
