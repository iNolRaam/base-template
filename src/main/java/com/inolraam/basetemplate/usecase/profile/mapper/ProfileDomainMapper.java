package com.inolraam.basetemplate.usecase.profile.mapper;

import com.inolraam.basetemplate.domain.Profile;
import com.inolraam.basetemplate.usecase.profile.dto.ProfileInput;
import com.inolraam.basetemplate.usecase.profile.dto.ProfileOutput;

import java.util.HashSet;

public final class ProfileDomainMapper {

    private ProfileDomainMapper() {}

    public static Profile toDomain(ProfileInput input) {
        if (input == null) return null;
        final String formattedName = formatNameToDomain(input.getName());
        Profile.ProfileBuilder<?, ?> builder = Profile.builder()
                .name(formattedName)
                .visible(input.getVisible());
        
        Profile profile = builder.build();
        
        // Add roles if they exist
        if (input.getRoles() != null) {
            input.getRoles().forEach(profile::addRole);
        }
        
        return profile;
    }

    public static Profile toDomain(long id, ProfileInput input) {
        if (input == null) return null;
        final String formattedName = formatNameToDomain(input.getName());
        Profile.ProfileBuilder<?, ?> builder = Profile.builder()
                .id(id)
                .name(formattedName)
                .visible(input.getVisible());
        
        Profile profile = builder.build();
        
        // Add roles if they exist
        if (input.getRoles() != null) {
            input.getRoles().forEach(profile::addRole);
        }
        
        return profile;
    }

    public static ProfileOutput toOutput(Profile domain) {
        if (domain == null) return null;
        return ProfileOutput.builder()
                .id(domain.getId())
                .name(domain.getName())
                .visible(domain.getVisible())
                .roles(domain.getRoles() != null ? new HashSet<>(domain.getRoles()) : new HashSet<>())
                .build();
    }

    private static String formatNameToDomain(String name) {
        return name.toLowerCase();
    }
}