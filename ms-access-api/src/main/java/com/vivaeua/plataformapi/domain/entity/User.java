package com.vivaeua.plataformapi.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @NotBlank
    @Email
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @Column(name = "PASSWORD", nullable = false, length = 200)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 25)
    private Role role = Role.ROLE_USER;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 25)
    private Status status = Status.STATUS_ACTIVE;
    @Column(name = "avatar")
    private String avatar;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date created_at;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updated_at;

    public enum Role{
        ROLE_CLIENT, ROLE_ADMIN, ROLE_USER
    }

    public enum Status{
        STATUS_ACTIVE, STATUS_DISABLED
    }


}
