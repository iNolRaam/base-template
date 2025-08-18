package com.inolraam.basetemplate.adapter.in.response.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inolraam.basetemplate.adapter.in.response.Response;
import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse implements Serializable, Response {
    @Serial
    private static final long serialVersionUID = 1L;

    private Object data;
}
