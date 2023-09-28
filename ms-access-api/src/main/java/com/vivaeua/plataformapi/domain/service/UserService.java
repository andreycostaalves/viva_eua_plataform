package com.vivaeua.plataformapi.domain.service;

import com.vivaeua.plataformapi.domain.entity.User;
import com.vivaeua.plataformapi.infra.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    public User create(User user) throws Exception {
        if(!validateEmail(user.getEmail())){
            throw new Exception("Fill in correctly with a valid email address.");
        }
        User userAlreadyExists = userRepository.findByEmail(user.getEmail());
        if (userAlreadyExists != null ) {
            throw new Exception("This user already exists.");
        }
        this.userRepository.save(user);
        return user;
    }


    public User findById(Long id) throws Exception {
       return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User findByEmail(String email) throws Exception {
        if (email.isEmpty()) {
            throw new Exception("Email should not be empty.");
        }
        User user = userRepository.findByEmail(email);
        if(!user.getEmail().isEmpty()){
            throw new Exception("This user already exists.");
        }
        return user;
    }



    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User update(Long id, User user) throws Exception {
        User userExists = findById(id);
        if(userExists == null){
            throw new Exception("User Not found by id: " + id);
        }
        userExists.setName(user.getName());
        userExists.setEmail(user.getEmail());
        userExists.setRole(user.getRole());
        userExists.setStatus(user.getStatus());

        user = userExists;
        return  userRepository.save(user);

    }

    public void delete(Long id) throws Exception {
        User user = findById(id);
        if(user == null){
            throw new Exception("User ID not Found!");
        }
        userRepository.delete(user);
    }


    public User saveUser(User user) {

        return userRepository.save(user);
    }

    public User editPassword(Long id, String currentpassword, String newPassword, String confirmPassword) throws Exception {
        if(!newPassword.equals(confirmPassword)){
            throw new RuntimeException("New password does not match password confirmation");
        }
        User user = findById(id);
        if (!user.getPassword().equals(currentpassword)){
            throw new RuntimeException("Your password is not correct.");
        }
        user.setPassword(newPassword);
        userRepository.save(user);
        return user;
    }

    public boolean validateEmail(String email) {
        // Expressão regular para validar um email
        String regex = "^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$";

        // Compila a regex em um padrão (Pattern)
        Pattern pattern = Pattern.compile(regex);

        // Cria um objeto Matcher para aplicar a regex ao email
        Matcher matcher = pattern.matcher(email);

        // Verifica se o email corresponde à regex
        return matcher.matches();
    }
}
