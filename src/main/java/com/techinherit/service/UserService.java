package com.techinherit.service;

import com.techinherit.dto.UserCreateDto;
import com.techinherit.dto.UserResponseDto;
import com.techinherit.entity.Users;
import com.techinherit.enums.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService {

    Users findCurrentUser() throws Exception;

    Page<UserResponseDto> findByUserRole(UserRole role, Pageable pg) throws Exception;

    public void createUser(UserCreateDto dto) throws Exception;
}
