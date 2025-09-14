package com.inolraam.basetemplate.common.exception;

import com.inolraam.basetemplate.common.constant.EntityType;

import lombok.Getter;

@Getter
public class ResourceInUseException extends RuntimeException{
    private static final String DEFAULT_MSG = "The resource %s is in use. id = %s";
    private final String id;
    private final EntityType entityType;

    public ResourceInUseException(EntityType entityType, long id){
        super(String.format(DEFAULT_MSG, entityType.getLabel(), String.valueOf(id)));
        this.id = String.valueOf(id);
        this.entityType = entityType;
    }
}
