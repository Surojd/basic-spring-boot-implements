package com.techinherit.basic.service.impl;

import com.techinherit.basic.dao.UserRepository;
import com.techinherit.basic.dto.UserCreateDto;
import com.techinherit.basic.dto.UserResponseDto;
import com.techinherit.yourpackage.entity.Users;
import com.techinherit.basic.enums.ErrorCode;
import com.techinherit.basic.enums.UserRole;
import com.techinherit.basic.enums.UserStatus;
import com.techinherit.basic.mapper.UserMapper;
import com.techinherit.basic.response.MyException;
import com.techinherit.basic.security.OnlineUser;
import com.techinherit.basic.service.UserService;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    UserMapper mapper = UserMapper.INSTANCE;

    @PostConstruct
    public void admin() {
        List<Users> list = userDao.findByUserRole(UserRole.ROLE_ADMIN);
        if (list.isEmpty()) {
            Users user = new Users();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setFirstName("admin");
            user.setLastName("admin");
            user.setUserRole(UserRole.ROLE_ADMIN);
            user.setUserStatus(UserStatus.ACTIVE);
            user.setLastPasswordResetDate(new Date());
            user.setLastLoginDate(new Date());
            userDao.save(user);
        }
    }

    @Override
    public Users findCurrentUser() throws Exception {
        try {
            return userDao.findById(getId()).orElse(null);
        } catch (Exception ex) {
            return null;
        }
    }

    private Integer getId() throws Exception {
        OnlineUser user = (OnlineUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }

    @Override
    public Page<UserResponseDto> findByUserRole(UserRole role, Pageable pg) throws Exception {
        Page<Users> page = userDao.findByUserRole(role, pg);
        List<UserResponseDto> list = UserMapper.INSTANCE.toUserResponseList((List<Users>) page.getContent());
        Page<UserResponseDto> pageResp = new PageImpl<>(list, page.getPageable(), page.getTotalElements());
        return pageResp;
    }

    @Override
    public void createUser(UserCreateDto dto) throws Exception {
        Users user = findCurrentUser();
        if (dto.getUserRole() == UserRole.ROLE_ADMIN && user.getUserRole() != UserRole.ROLE_ADMIN) {
            throw new MyException(ErrorCode.A403);
        }
        Users newUser = mapper.toUser(dto);
    }
}
