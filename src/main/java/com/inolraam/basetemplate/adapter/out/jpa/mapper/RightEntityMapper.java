package com.inolraam.basetemplate.adapter.out.jpa.mapper;

import com.inolraam.basetemplate.adapter.out.jpa.entity.RightEntity;
import com.inolraam.basetemplate.adapter.out.jpa.entity.TypeRightEntity;
import com.inolraam.basetemplate.domain.Right;

public final class RightEntityMapper {

    public static RightEntity toEntity(Right right) {
        if (right == null) return null;

        final RightEntity entity = new RightEntity();
        entity.setId(right.getId());
        entity.setIdTypeRight(new TypeRightEntity(right.getIdTypeRight()));
        entity.setName(right.getName());

        return entity;
    }

    public static Right toDomain(RightEntity entity) {
        if (entity == null) return null;

        return Right.builder()
                .id(entity.getId())
                .idTypeRight(entity.getIdTypeRight().getId())
                .visible(entity.isVisible())
                .build();
    }
}
