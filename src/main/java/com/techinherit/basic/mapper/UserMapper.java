package com.techinherit.basic.mapper;

import com.techinherit.basic.dto.UserResponseDto;
import com.techinherit.basic.dto.UserCreateDto;
import com.techinherit.yourpackage.entity.Users;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    Users toUser(UserCreateDto cdr);

    List<UserResponseDto> toUserResponseList(List<Users> page);
}
