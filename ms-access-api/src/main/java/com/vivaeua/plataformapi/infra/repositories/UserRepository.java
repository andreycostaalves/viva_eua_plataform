package com.vivaeua.plataformapi.infra.repositories;

import com.vivaeua.plataformapi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    Optional<User> findByUsername(String username);
    @Query("select u.role from user u where like : username")
    User.Role findRoleByUsername(String username);
}
