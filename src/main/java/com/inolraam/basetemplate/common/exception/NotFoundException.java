package com.inolraam.basetemplate.common.exception;

import lombok.Getter;

@Getter
public class NotFoundException  extends RuntimeException{
    private static final String DEFAULT_MSG = "Record not found with %s = %s";
    private static final String BY_ID = "ID";
    private static final String BY_NAME = "NAME";
    private final String value;
    private final String searchBy;

    public NotFoundException(Long id){
        super(String.format(DEFAULT_MSG, BY_ID, String.valueOf(id)));
        this.searchBy = BY_ID;
        this.value = String.valueOf(id);
    }

    public NotFoundException(String name){
        super(String.format(DEFAULT_MSG, BY_NAME, name));
        this.searchBy = BY_NAME;
        this.value = name;
    }
}
