package com.techinherit.service.impl;

import com.techinherit.dao.UserRepository;
import com.techinherit.dto.UserCreateDto;
import com.techinherit.dto.UserResponseDto;
import com.techinherit.entity.Users;
import com.techinherit.enums.ErrorCode;
import com.techinherit.enums.UserRole;
import com.techinherit.enums.UserStatus;
import com.techinherit.mapper.UserMapper;
import com.techinherit.response.MyException;
import com.techinherit.security.OnlineUser;
import com.techinherit.service.UserService;
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
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
//        for (int i = 0; i < 100; i++) {
//            StringBuilder buffer = new StringBuilder(targetStringLength);
//            for (int l = 0; l < targetStringLength; l++) {
//                int randomLimitedInt = leftLimit + (int) (Math.random() * (rightLimit - leftLimit + 1));
//                buffer.append((char) randomLimitedInt);
//            }
//            String generatedString = buffer.toString();
//            Users user = new Users();
//            user.setUsername(generatedString);
//            user.setPassword(passwordEncoder.encode("admin"));
//            user.setFirstName("admin");
//            user.setLastName("admin");
//            user.setUserRole(UserRole.ROLE_DRIVER);
//            user.setUserStatus(UserStatus.ACTIVE);
//            user.setLastPasswordResetDate(new Date());
//            user.setLastLoginDate(new Date());
//            userDao.save(user);
//        }
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
