package com.inolraam.basetemplate.adapter.out.jpa.mapper;

import com.inolraam.basetemplate.adapter.out.jpa.entity.TypeRightEntity;
import com.inolraam.basetemplate.domain.TypeRight;

public final class TypeRightEntityMapper {

    private TypeRightEntityMapper() {}

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
        final TypeRightEntity entity = new TypeRightEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setVisible(domain.getVisible());
        return entity;
    }
}
