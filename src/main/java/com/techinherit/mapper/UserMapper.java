package com.techinherit.mapper;

import com.techinherit.dto.UserResponseDto;
import com.techinherit.dto.UserCreateDto;
import com.techinherit.entity.Users;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Users toUser(UserCreateDto cdr);

    List<UserResponseDto> toUserResponseList(List<Users> page);
}
