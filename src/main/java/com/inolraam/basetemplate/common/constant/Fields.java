package com.inolraam.basetemplate.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Fields {
    NAME("name"),
    ID("id"),
    RIGHTS("rights");

    private final String label;
}
