package com.inolraam.basetemplate.adapter.in.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOut implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Object data;
    private String errorMessage;
    private Map<String, String> fields;
}
