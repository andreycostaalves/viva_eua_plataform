package com.vivaeua.plataformapi.application.dto;

import com.vivaeua.plataformapi.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {


    private String name;
    private String email;
    private String password;
    private String avatar;
    private User.Role role = User.Role.ROLE_USER;
    private User.Status status = User.Status.STATUS_ACTIVE;


}

