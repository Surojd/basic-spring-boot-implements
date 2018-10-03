package com.techinherit.basic.mapper;

import com.techinherit.basic.dto.UserResponseDto;
import com.techinherit.basic.dto.UserCreateDto;
import com.techinherit.basic.entity.Users;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Users toUser(UserCreateDto cdr);

    List<UserResponseDto> toUserResponseList(List<Users> page);
}
