package com.vivaeua.plataformapi.jwt;

import com.vivaeua.plataformapi.domain.entity.User;
import com.vivaeua.plataformapi.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        return new JwtUserDetails(user);
    }

    public JwtToken getTokenAuthenticated(String username){
        User.Role role =userService.findRoleForUsername(username);
        return JwtUtils.createToken(username, role.name().substring("ROLE_".length()));
    }
}
