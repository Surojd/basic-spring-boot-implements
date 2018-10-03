package com.techinherit.basic.service;

import com.techinherit.basic.dto.UserCreateDto;
import com.techinherit.basic.dto.UserResponseDto;
import com.techinherit.yourpackage.entity.Users;
import com.techinherit.basic.enums.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService {

    Users findCurrentUser() throws Exception;

    Page<UserResponseDto> findByUserRole(UserRole role, Pageable pg) throws Exception;

    public void createUser(UserCreateDto dto) throws Exception;
}
