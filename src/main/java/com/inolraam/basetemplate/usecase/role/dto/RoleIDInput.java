package com.inolraam.basetemplate.usecase.role.dto;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class RoleIDInput implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Long id;

    public RoleIDInput(Long id) {
        this.id = id;
    }
}