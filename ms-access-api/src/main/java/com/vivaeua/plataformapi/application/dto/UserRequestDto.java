package com.vivaeua.plataformapi.application.dto;

import com.vivaeua.plataformapi.domain.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    @NotBlank
    private String name;
    @NotBlank
    @Email(regexp = "^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$", message = "Fill in correctly with a valid email address.")
    private String email;
    @NotBlank
    @Size(min = 6, max = 6, message = "The password must be 6 digits long!")
    private String password;
    private String avatar;
    private User.Role role = User.Role.ROLE_USER;
    private User.Status status = User.Status.STATUS_ACTIVE;


}

