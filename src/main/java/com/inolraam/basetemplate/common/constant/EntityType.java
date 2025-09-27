package com.inolraam.basetemplate.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EntityType {
    RIGHT("Right"),
    TYPE_RIGHT("Type Right"),
    ROLE("Role"),
    ROLE_RIGHT("Role Right"),
    PROFILE("Profile"),
    USER("User");

    private final String label;
}