package com.inolraam.basetemplate.usecase.user.mapper;

import com.inolraam.basetemplate.domain.User;
import com.inolraam.basetemplate.usecase.user.dto.UserInput;
import com.inolraam.basetemplate.usecase.user.dto.UserOutput;

import java.util.HashSet;

public final class UserDomainMapper {

    private UserDomainMapper() {}

    public static User toDomain(UserInput input) {
        if (input == null) return null;
        final String formattedEmail = formatEmailToDomain(input.getEmail());
        final String formattedUsername = formatUsernameToDomain(input.getUsername());
        
        User.UserBuilder<?, ?> builder = User.builder()
                .email(formattedEmail)
                .username(formattedUsername)
                .emailVerified(false) // New users start with unverified email
                .status(input.getStatus());
        
        User user = builder.build();
        
        // Add profiles if they exist
        if (input.getProfiles() != null) {
            input.getProfiles().forEach(user::addProfile);
        }
        
        return user;
    }

    public static User toDomain(long id, UserInput input) {
        if (input == null) return null;
        final String formattedEmail = formatEmailToDomain(input.getEmail());
        final String formattedUsername = formatUsernameToDomain(input.getUsername());
        
        User.UserBuilder<?, ?> builder = User.builder()
                .id(id)
                .email(formattedEmail)
                .username(formattedUsername)
                .emailVerified(false) // Maintain current verification status in update scenarios
                .status(input.getStatus());
        
        User user = builder.build();
        
        // Add profiles if they exist
        if (input.getProfiles() != null) {
            input.getProfiles().forEach(user::addProfile);
        }
        
        return user;
    }

    public static UserOutput toOutput(User domain) {
        if (domain == null) return null;
        return UserOutput.builder()
                .id(domain.getId())
                .email(domain.getEmail())
                .username(domain.getUsername())
                .emailVerified(domain.isEmailVerified())
                .status(domain.getStatus())
                .lastLogin(domain.getLastLogin())
                .profiles(domain.getProfiles() != null ? new HashSet<>(domain.getProfiles()) : new HashSet<>())
                .build();
    }

    private static String formatEmailToDomain(String email) {
        return email != null ? email.toLowerCase().trim() : null;
    }

    private static String formatUsernameToDomain(String username) {
        return username != null ? username.toLowerCase().trim() : null;
    }
}