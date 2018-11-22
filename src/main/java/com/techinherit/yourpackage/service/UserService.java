package com.techinherit.yourpackage.service;

import com.techinherit.yourpackage.dto.UserCreateDto;
import com.techinherit.yourpackage.dto.UserResponseDto;
import com.techinherit.yourpackage.enums.UserRole;
import com.techinherit.yourpackage.database.Users;
import com.techinherit.yourpackage.dto.ForgetDto;
import com.techinherit.yourpackage.dto.ResetPasswordDto;
import com.techinherit.yourpackage.dto.ShopCreateDto;
import com.techinherit.yourpackage.dto.VerificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService {

    Users findCurrentUser() throws Exception;

    Page<UserResponseDto> findByUserRole(UserRole role, Pageable pg) throws Exception;

    public Users createUser(UserCreateDto dto) throws Exception;

    public void updateUser(UserCreateDto dto, Integer userId) throws Exception;

    public Users findByUsername(String username) throws Exception;

    public Users verifyUser(VerificationDto dto) throws Exception;

    public void resetPassword(ResetPasswordDto dto) throws Exception;

    public void forgotPassword(ForgetDto dto) throws Exception;
    
    public void sendVerificationCode(String phoneNumber)throws Exception;
    
}
