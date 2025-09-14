package com.inolraam.basetemplate.common.exception;

import com.inolraam.basetemplate.common.constant.EntityType;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private static final String DEFAULT_MSG = "%s not found with %s = %s";
    private static final String BY_ID = "ID";
    private static final String BY_NAME = "NAME";
    private final String value;
    private final String searchBy;
    private final EntityType entityType;

    public NotFoundException(EntityType entityType, Long id) {
        super(String.format(DEFAULT_MSG, entityType.getLabel(), BY_ID, String.valueOf(id)));
        this.searchBy = BY_ID;
        this.value = String.valueOf(id);
        this.entityType = entityType;
    }

    public NotFoundException(EntityType entityType, String name) {
        super(String.format(DEFAULT_MSG, entityType.getLabel(), BY_NAME, name));
        this.searchBy = BY_NAME;
        this.value = name;
        this.entityType = entityType;
    }
}
