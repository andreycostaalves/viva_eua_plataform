package com.vivaeua.plataformapi.application.dto.mapper;

import com.vivaeua.plataformapi.application.dto.UserRequestDto;
import com.vivaeua.plataformapi.application.dto.UserResponseDto;
import com.vivaeua.plataformapi.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserRequestDto userToUserRequestDto(User user);

    User userRequestDtoToUser(UserRequestDto userRequestDto);

    UserResponseDto userToUserResponseDto(User user);

    User userResponseDtoToUser(UserResponseDto userResponseDto);


}


