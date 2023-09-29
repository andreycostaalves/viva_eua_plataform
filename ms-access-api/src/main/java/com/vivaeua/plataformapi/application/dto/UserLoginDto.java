package com.vivaeua.plataformapi.application.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLoginDto {

    @NotBlank
    @Email(regexp = "^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$", message = "Fill in correctly with a valid email address.")
    private String username;
    @NotBlank
    @Size(min = 6, max = 6, message = "The password must be 6 digits long!")
    private String password;
}
