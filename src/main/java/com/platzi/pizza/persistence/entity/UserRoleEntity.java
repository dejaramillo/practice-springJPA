package com.platzi.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@IdClass(UserRoleId.class)
@Table(name = "user_role")
@NoArgsConstructor
public class UserRoleEntity {

    @Id
    @Column(nullable = false, length = 20)
    private String role;

    @Id
    @Column(length = 20, nullable = false)
    private String username;


    @Column(name = "granted_date", columnDefinition = "DATETIME")
    private LocalDateTime grantedDate;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private UserEntity user;

}
