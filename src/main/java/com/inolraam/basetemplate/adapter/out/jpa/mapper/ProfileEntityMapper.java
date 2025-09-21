package com.inolraam.basetemplate.adapter.out.jpa.mapper;

import com.inolraam.basetemplate.adapter.out.jpa.entity.ProfileEntity;
import com.inolraam.basetemplate.domain.Profile;

import java.util.HashSet;

public final class ProfileEntityMapper {

    private ProfileEntityMapper() {}

    public static Profile toDomain(ProfileEntity entity) {
        if(entity == null) return null;
        
        Profile.ProfileBuilder<?, ?> builder = Profile.builder()
                .id(entity.getId())
                .name(entity.getName())
                .visible(entity.getVisible());
        
        Profile profile = builder.build();
        
        // Add roles directly as both use Set<Long>
        if (entity.getRoles() != null) {
            entity.getRoles().forEach(profile::addRole);
        }
        
        return profile;
    }

    public static ProfileEntity toEntity(Profile domain) {
        if(domain == null) return null;
        
        final ProfileEntity entity = new ProfileEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setVisible(domain.getVisible());
        
        // Set roles directly as both use Set<Long>
        if (domain.getRoles() != null) {
            entity.setRoles(new HashSet<>(domain.getRoles()));
        }
        
        return entity;
    }
}