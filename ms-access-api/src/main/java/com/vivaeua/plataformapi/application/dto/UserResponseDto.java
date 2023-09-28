package com.vivaeua.plataformapi.application.dto;

import com.vivaeua.plataformapi.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {


    private String name;
    private String email;
    private String avatar;
    private User.Role role;



}

