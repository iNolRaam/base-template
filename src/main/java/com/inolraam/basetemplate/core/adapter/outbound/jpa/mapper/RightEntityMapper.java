package com.inolraam.basetemplate.core.adapter.outbound.jpa.mapper;

import com.inolraam.basetemplate.core.adapter.outbound.jpa.entity.RightEntity;
import com.inolraam.basetemplate.core.adapter.outbound.jpa.entity.TypeRightEntity;
import com.inolraam.basetemplate.core.domain.model.Right;

public final class RightEntityMapper {

    private RightEntityMapper() {}

    public static RightEntity toEntity(Right right) {
        if (right == null) return null;

        final RightEntity entity = new RightEntity();
        entity.setId(right.getId());
        entity.setIdTypeRight(new TypeRightEntity(right.getIdTypeRight()));
        entity.setName(right.getName());
        entity.setVisible(right.getVisible());

        return entity;
    }

    public static Right toDomain(RightEntity entity) {
        if (entity == null) return null;

        return Right.builder()
                .id(entity.getId())
                .name(entity.getName())
                .idTypeRight(entity.getIdTypeRight().getId())
                .visible(entity.getVisible())
                .build();
    }
}
