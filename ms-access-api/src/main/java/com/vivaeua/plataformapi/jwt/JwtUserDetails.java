package com.vivaeua.plataformapi.jwt;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;


public class JwtUserDetails extends User {

    private final com.vivaeua.plataformapi.domain.entity.User user;

    public JwtUserDetails(com.vivaeua.plataformapi.domain.entity.User user) {
        super(user.getName(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().name()));
        this.user = user;
    }

    public Long getId(){
        return this.user.getId();

    }

    public String getRole() {
        return this.user.getRole().name();
    }
}
