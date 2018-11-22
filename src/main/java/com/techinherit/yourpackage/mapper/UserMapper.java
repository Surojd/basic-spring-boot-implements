package com.techinherit.yourpackage.mapper;

import com.techinherit.yourpackage.dto.UserResponseDto;
import com.techinherit.yourpackage.dto.UserCreateDto;
import com.techinherit.yourpackage.database.Users;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    Users toUser(UserCreateDto cdr);

    List<UserResponseDto> toUserResponseList(List<Users> page);
}
