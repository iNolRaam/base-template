package com.inolraam.basetemplate.config.swagger;

import lombok.Getter;

@Getter
public enum CommonResponse {
    DEFAULT_200("200", "The request was processed successfully."),
    DEFAULT_400("400", "The request is malformed."),
    DEFAULT_404("404", "The resource has not been found.");


    private final String code;
    private final String description;

    private CommonResponse(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
