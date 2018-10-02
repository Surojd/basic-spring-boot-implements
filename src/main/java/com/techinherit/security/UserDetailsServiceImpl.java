package com.techinherit.security;

import com.techinherit.dao.UserRepository;
import com.techinherit.entity.Users;
import com.techinherit.enums.UserStatus;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return convertUserDetails(user);
        }
    }

    private OnlineUser convertUserDetails(Users user) {
        OnlineUser user1 = new OnlineUser();
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setUsername(user.getUsername());
        user1.setEnabled(user.getUserStatus() == UserStatus.ACTIVE);
        user1.setId(user.getId());
        user1.setPassword(user.getPassword());
        user1.setAuthorities(buildUserAuthority(user));
        user1.setLastPasswordResetDate(user.getLastPasswordResetDate());
        user1.setLastLoginDate(user.getLastPasswordResetDate());
        return user1;
    }

    private List<GrantedAuthority> buildUserAuthority(Users userEntity) {
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        grantedAuthoritySet.add(new SimpleGrantedAuthority(userEntity.getUserRole().toString()));
        return new ArrayList<>(grantedAuthoritySet);
    }

}
