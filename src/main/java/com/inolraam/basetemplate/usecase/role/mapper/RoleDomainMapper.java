package com.inolraam.basetemplate.usecase.role.mapper;

import com.inolraam.basetemplate.domain.Role;
import com.inolraam.basetemplate.usecase.role.dto.RoleInput;
import com.inolraam.basetemplate.usecase.role.dto.RoleOutput;

import java.util.HashSet;

public final class RoleDomainMapper {

    private RoleDomainMapper() {}

    public static Role toDomain(RoleInput input) {
        if (input == null) return null;
        final String formattedName = formatNameToDomain(input.getName());
        Role.RoleBuilder<?, ?> builder = Role.builder()
                .name(formattedName)
                .visible(input.getVisible());
        
        Role role = builder.build();
        
        // Add rights if they exist
        if (input.getRights() != null) {
            input.getRights().forEach(role::addRight);
        }
        
        return role;
    }

    public static Role toDomain(long id, RoleInput input) {
        if (input == null) return null;
        final String formattedName = formatNameToDomain(input.getName());
        Role.RoleBuilder<?, ?> builder = Role.builder()
                .id(id)
                .name(formattedName)
                .visible(input.getVisible());
        
        Role role = builder.build();
        
        // Add rights if they exist
        if (input.getRights() != null) {
            input.getRights().forEach(role::addRight);
        }
        
        return role;
    }

    public static RoleOutput toOutput(Role domain) {
        if (domain == null) return null;
        return RoleOutput.builder()
                .id(domain.getId())
                .name(domain.getName())
                .visible(domain.getVisible())
                .rights(domain.getRights() != null ? new HashSet<>(domain.getRights()) : new HashSet<>())
                .build();
    }

    private static String formatNameToDomain(String name) {
        return name.toLowerCase();
    }
}