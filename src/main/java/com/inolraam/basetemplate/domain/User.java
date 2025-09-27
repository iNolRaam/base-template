package com.inolraam.basetemplate.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String email;
    private final String username;
    private final boolean emailVerified;
    private final String status;
    private final LocalDateTime lastLogin;
    private final Set<Long> profiles = new HashSet<>();

    public void addProfile(Long profileId) {
        profiles.add(profileId);
    }

    public void removeProfile(Long profileId) {
        profiles.remove(profileId);
    }

    public Set<Long> getProfiles() {
        return Collections.unmodifiableSet(profiles);
    }

    public boolean hasProfiles() {
        return !profiles.isEmpty();
    }

    public boolean hasProfile(Long profileId) {
        return profiles.contains(profileId);
    }

    public User updateWith(User newData) {
        // Create updated user with new data
        User updatedUser = User.builder()
                .id(this.getId())
                .email(newData.getEmail())
                .username(newData.getUsername())
                .emailVerified(newData.isEmailVerified())
                .status(newData.getStatus())
                .lastLogin(newData.getLastLogin())
                .build();
        
        // Add profiles from new data
        if (newData.getProfiles() != null) {
            newData.getProfiles().forEach(updatedUser::addProfile);
        }
        
        return updatedUser;
    }

    public User markEmailAsVerified() {
        return this.toBuilder()
                .emailVerified(true)
                .build();
    }

    public User updateLastLogin(LocalDateTime loginTime) {
        return this.toBuilder()
                .lastLogin(loginTime)
                .build();
    }

    public User changeStatus(String newStatus) {
        return this.toBuilder()
                .status(newStatus)
                .build();
    }
}