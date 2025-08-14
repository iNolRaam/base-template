package com.inolraam.basetemplate.adapter.out.jpa.mapper;

import com.inolraam.basetemplate.adapter.out.jpa.entity.RightEntity;
import com.inolraam.basetemplate.domain.Right;

public final class RightEntityMapper {

    public static RightEntity toEntity(Right right) {
        if (right == null) return null;

        final RightEntity entity = new RightEntity();
        entity.setId(right.getId());
        entity.setName(right.getName());
        return entity;
    }

    public static Right toDomain(RightEntity entity) {
        if (entity == null) return null;

        return new Right(entity.getId(), entity.getName());
    }
}
