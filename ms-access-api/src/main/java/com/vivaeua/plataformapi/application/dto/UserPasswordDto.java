package com.vivaeua.plataformapi.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserPasswordDto {

    private String currentpassword;
    private String newPassword;
    private String confirmPassword;
}
