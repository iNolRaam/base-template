package com.inolraam.basetemplate.usecase.dtoglobal;

import com.inolraam.basetemplate.adapter.in.validation.MessageCodes;
import com.inolraam.basetemplate.adapter.in.validation.RegexPatterns;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Getter
@SuperBuilder
public class BaseSystemCatalogDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = MessageCodes.NOT_BLANK)
    @Size(min = 3, max = 100, message =  MessageCodes.SIZE)
    @Pattern(regexp = RegexPatterns.ONLY_FOR_SYSTEM_PARAMETERS, message = MessageCodes.REGEX_ONLY_FOR_SYSTEM_PARAMETERS)
    private String name;

    @NotNull(message = MessageCodes.NOT_NULL)
    private Boolean visible;

    protected BaseSystemCatalogDto(){}
}
