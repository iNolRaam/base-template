package com.inolraam.basetemplate.adapter.out.jpa.mapper;

import com.inolraam.basetemplate.adapter.out.jpa.entity.RoleEntity;
import com.inolraam.basetemplate.domain.Role;

import java.util.HashSet;

public final class RoleEntityMapper {

    private RoleEntityMapper() {}

    public static Role toDomain(RoleEntity entity) {
        if(entity == null) return null;
        
        Role.RoleBuilder<?, ?> builder = Role.builder()
                .id(entity.getId())
                .name(entity.getName())
                .visible(entity.getVisible());
        
        Role role = builder.build();
        
        // Add rights directly as both use Set<Long>
        if (entity.getRights() != null) {
            entity.getRights().forEach(role::addRight);
        }
        
        return role;
    }

    public static RoleEntity toEntity(Role domain) {
        if(domain == null) return null;
        
        final RoleEntity entity = new RoleEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setVisible(domain.getVisible());
        
        // Set rights directly as both use Set<Long>
        if (domain.getRights() != null) {
            entity.setRights(new HashSet<>(domain.getRights()));
        }
        
        return entity;
    }
}