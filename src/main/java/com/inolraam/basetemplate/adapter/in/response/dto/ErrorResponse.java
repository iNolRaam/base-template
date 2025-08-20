package com.inolraam.basetemplate.adapter.in.response.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inolraam.basetemplate.adapter.in.response.Response;
import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse implements Serializable, Response {
    @Serial
    private static final long serialVersionUID = 1L;

    private String errorMessage;
    private Object[] InvalidFields;
}
