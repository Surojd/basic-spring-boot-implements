package com.techinherit.yourpackage.service.impl;

import com.techinherit.basic.response.MyException;
import com.techinherit.yourpackage.dao.UserRepository;
import com.techinherit.yourpackage.dto.UserCreateDto;
import com.techinherit.yourpackage.dto.UserResponseDto;
import com.techinherit.yourpackage.enums.UserRole;
import com.techinherit.yourpackage.enums.UserStatus;
import com.techinherit.yourpackage.mapper.UserMapper;
import com.techinherit.basic.security.OnlineUser;
import com.techinherit.yourpackage.dao.PhoneNumberRepository;
import com.techinherit.yourpackage.database.PhoneNumbers;
import com.techinherit.yourpackage.service.UserService;
import com.techinherit.yourpackage.database.Users;
import com.techinherit.yourpackage.dto.ForgetDto;
import com.techinherit.yourpackage.dto.ResetPasswordDto;
import com.techinherit.yourpackage.dto.ShopCreateDto;
import com.techinherit.yourpackage.dto.VerificationDto;
import com.techinherit.yourpackage.enums.ErrorCode;
import com.techinherit.yourpackage.service.EmailService;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    public EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper mapper;

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
        List<UserResponseDto> list = mapper.toUserResponseList((List<Users>) page.getContent());
        Page<UserResponseDto> pageResp = new PageImpl<>(list, page.getPageable(), page.getTotalElements());
        return pageResp;
    }

    @Value("${app.sms-expiration}")
    Long expiration;

    @Override
    public Users createUser(UserCreateDto dto) throws Exception {
        Long max = System.currentTimeMillis();
        phoneNumberRepository.findByPhoneNumberAndVerificationCodeAndSignupDateBetween(dto.getUsername(),
                dto.getVerificationCode(), max - expiration, max).orElseThrow(() -> {
            return new MyException(ErrorCode.V008);
        });
        Users newUser = mapper.toUser(dto);
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        newUser.setUserRole(UserRole.ROLE_USER);
        newUser.setUserStatus(UserStatus.ACTIVE);
        newUser.setLastLoginDate(new Date());
        newUser.setLastPasswordResetDate(new Date());
        userDao.save(newUser);
        return newUser;
    }

    @Override
    public void updateUser(UserCreateDto dto, Integer userId) throws Exception {
        Users user = userDao.findById(userId).orElseThrow(() -> {
            return new MyException(ErrorCode.U001);
        });
        user = mapper.toUser(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setUserRole(UserRole.ROLE_USER);
        user.setUserStatus(UserStatus.ACTIVE);
        user.setLastLoginDate(new Date());
        user.setLastPasswordResetDate(new Date());
        userDao.save(user);
    }

    @Override
    public Users findByUsername(String username) throws Exception {
        Users user = userDao.findByUsername(username);
        if (user == null) {
            throw new Exception(ErrorCode.V006.toString());
        }
        return user;
    }

    @Override
    public void forgotPassword(ForgetDto dto) throws Exception {
        Users user = userDao.findByUsernameOrEmail(dto.getUsername(), dto.getUsername()).orElseThrow(() -> {
            return new MyException(ErrorCode.V006);
        });
        Random rNo = new Random();
        int verificationCode = rNo.nextInt((9999 - 1000) + 1) + 1000;
        user.setVerificationCode(verificationCode);
        userDao.save(user);
        emailService.sendEmailMessage(user.getUsername(), "Confirm your email ",
                "Verification code: " + verificationCode);
    }

    @Override
    public Users verifyUser(VerificationDto dto) throws Exception {
        Users user = userDao.findByUsernameAndVerificationCode(dto.getUsername(), dto.getVerificationCode()).orElseThrow(() -> {
            return new MyException(ErrorCode.V006);
        });
        return user;
    }

    @Override
    public void resetPassword(ResetPasswordDto dto) throws Exception {
        if (dto.getPassword() == null ? dto.getRepassword() == null
                : dto.getPassword().equals(dto.getRepassword())) {
            Users user = verifyUser(dto);
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            userDao.save(user);
        } else {
            throw new MyException(ErrorCode.V010);
        }
    }

    @Autowired
    PhoneNumberRepository phoneNumberRepository;

    @Override
    public void sendVerificationCode(String number) throws Exception {
        Users user = userDao.findByUsername(number);
        if (user == null) {
            PhoneNumbers phoneNumber = phoneNumberRepository.findById(number).orElse(new PhoneNumbers());
            //send code
            phoneNumber.setPhoneNumber(number);
            Random rNo = new Random();
            int verificationCode = rNo.nextInt((9999 - 1000) + 1) + 1000;
            phoneNumber.setVerificationCode(verificationCode);
            phoneNumber.setSignupDate(System.currentTimeMillis());
            phoneNumberRepository.save(phoneNumber);
        } else {
            throw new MyException(ErrorCode.U004);
        }
    }
}
