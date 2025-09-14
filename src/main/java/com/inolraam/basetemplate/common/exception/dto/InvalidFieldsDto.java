package com.inolraam.basetemplate.common.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

import com.inolraam.basetemplate.common.constant.Fields;

@Getter
@ToString
@AllArgsConstructor
public class InvalidFieldsDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Fields field;
    private String message;
}
