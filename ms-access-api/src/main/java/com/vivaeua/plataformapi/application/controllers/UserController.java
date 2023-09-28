package com.vivaeua.plataformapi.application.controllers;

import com.vivaeua.plataformapi.application.dto.UserPasswordDto;
import com.vivaeua.plataformapi.application.dto.UserRequestDto;
import com.vivaeua.plataformapi.application.dto.UserResponseDto;
import com.vivaeua.plataformapi.application.dto.mapper.UserMapper;
import com.vivaeua.plataformapi.domain.entity.User;
import com.vivaeua.plataformapi.domain.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/users")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        try {
            List<User> users = userService.findAll();
            List<UserResponseDto> userResponseDtos = users.stream()
                    .map(user -> UserMapper.INSTANCE.userToUserResponseDto(user))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(userResponseDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody UserRequestDto userRequestDto) {
        try {
            User user = UserMapper.INSTANCE.userRequestDtoToUser(userRequestDto);
            this.userService.create(user);
            return ResponseEntity.ok().body("User created succesfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            User user = userService.findById(id);

            UserResponseDto userResponseDto = UserMapper.INSTANCE.userToUserResponseDto(user);

            return ResponseEntity.ok().body(userResponseDto);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @PathVariable Long id, @RequestBody UserRequestDto userRequestDto) throws Exception {
        try {
            User user = UserMapper.INSTANCE.userRequestDtoToUser(userRequestDto);
            userService.update(id, user);
            UserResponseDto userResponseDto = UserMapper.INSTANCE.userToUserResponseDto(user);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok().body("User deleted!");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

    @PatchMapping("/avatar/{id}")
    public ResponseEntity<UserResponseDto> uploadAvatar(@PathVariable Long id, @RequestParam("avatar") String avatarUrl) {
        try {
            User user = userService.findById(id);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            user.setAvatar(avatarUrl);
            userService.saveUser(user);

            UserResponseDto userResponseDto = UserMapper.INSTANCE.userToUserResponseDto(user);

            return ResponseEntity.ok(userResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/password/{id}")
    public ResponseEntity<Void> updatePassword(
             @PathVariable Long id, @Valid @RequestBody UserPasswordDto userPasswordDto) throws Exception {
        try {

            User user =userService.editPassword( id,
                    userPasswordDto.getCurrentpassword(),
                    userPasswordDto.getNewPassword(),
                    userPasswordDto.getConfirmPassword()
            );
            return ResponseEntity.noContent().build();

        }catch (Exception e){
            throw new Exception( e.toString());
        }
    }



}
