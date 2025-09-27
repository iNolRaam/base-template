package com.inolraam.basetemplate.adapter.out.jpa.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "users")
public class UserEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_users_id")
    @SequenceGenerator(name = "seq_users_id", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 30, unique = true)
    private String username;
    
    @Column(name = "email_verified", nullable = false, columnDefinition = "boolean")
    private boolean emailVerified = false;

    @Column(nullable = false, length = 10)
    private String status;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "users_profiles",
            joinColumns = @JoinColumn(name = "id_user")
    )
    @Column(name = "id_profile")
    private Set<Long> profiles;
}