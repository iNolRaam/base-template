package com.inolraam.basetemplate.adapter.in.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Object data;
    private String errorMessage;
    private Map<String, String> InvalidFields;
}
