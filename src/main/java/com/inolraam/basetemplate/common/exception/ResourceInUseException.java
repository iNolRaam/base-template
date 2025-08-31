package com.inolraam.basetemplate.common.exception;

import lombok.Getter;

@Getter
public class ResourceInUseException extends RuntimeException{
    private static final String DEFAULT_MSG = "The resource is in use. ID = %d";
    private final String id;

    public ResourceInUseException(long id){
        super(String.format(DEFAULT_MSG, id));
        this.id = String.valueOf(id);
    }
}
