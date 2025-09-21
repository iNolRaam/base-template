package com.inolraam.basetemplate.usecase.profile.dto;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class ProfileIDInput implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Long id;

    public ProfileIDInput(Long id) {
        this.id = id;
    }
}
