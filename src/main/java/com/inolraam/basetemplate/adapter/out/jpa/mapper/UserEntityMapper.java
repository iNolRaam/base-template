package com.inolraam.basetemplate.adapter.out.jpa.mapper;

import com.inolraam.basetemplate.adapter.out.jpa.entity.UserEntity;
import com.inolraam.basetemplate.domain.User;

import java.util.HashSet;

public final class UserEntityMapper {

    private UserEntityMapper() {}

    public static User toDomain(UserEntity entity) {
        if(entity == null) return null;
        
        User.UserBuilder<?, ?> builder = User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .emailVerified(entity.isEmailVerified())
                .status(entity.getStatus())
                .lastLogin(entity.getLastLogin());
        
        User user = builder.build();
        
        // Add profiles directly as both use Set<Long>
        if (entity.getProfiles() != null) {
            entity.getProfiles().forEach(user::addProfile);
        }
        
        return user;
    }

    public static UserEntity toEntity(User domain) {
        if(domain == null) return null;
        
        final UserEntity entity = new UserEntity();
        entity.setId(domain.getId());
        entity.setEmail(domain.getEmail());
        entity.setUsername(domain.getUsername());
        entity.setEmailVerified(domain.isEmailVerified());
        entity.setStatus(domain.getStatus());
        entity.setLastLogin(domain.getLastLogin());
        
        // Set profiles directly as both use Set<Long>
        if (domain.getProfiles() != null) {
            entity.setProfiles(new HashSet<>(domain.getProfiles()));
        }
        
        return entity;
    }
}