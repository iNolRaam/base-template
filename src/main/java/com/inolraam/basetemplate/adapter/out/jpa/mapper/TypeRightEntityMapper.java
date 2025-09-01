package com.inolraam.basetemplate.adapter.out.jpa.mapper;

import com.inolraam.basetemplate.adapter.out.jpa.entity.TypeRightEntity;
import com.inolraam.basetemplate.domain.TypeRight;

import java.util.Optional;

public final class TypeRightEntityMapper {

    public static TypeRight toDomain(TypeRightEntity entity) {
        if(entity == null) return null;
        return TypeRight.builder()
                .id(entity.getId())
                .name(entity.getName())
                .visible(entity.getVisible())
                .build();
    }

    public static TypeRightEntity toEntity(TypeRight domain) {
        if(domain == null) return null;
        long id = Optional.ofNullable(domain.getId()).orElse(0L);
        final TypeRightEntity entity = new TypeRightEntity(id);
        entity.setName(domain.getName());
        entity.setVisible(domain.getVisible());
        return entity;
    }
}
