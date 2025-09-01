package com.inolraam.basetemplate.usecase.dtoglobal;

import com.inolraam.basetemplate.adapter.in.validation.MessageCodes;
import com.inolraam.basetemplate.adapter.in.validation.RegexPatterns;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Getter
@SuperBuilder
@NoArgsConstructor
public class BaseCatalogDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = MessageCodes.NOT_BLANK)
    @Size(min = 3, max = 100, message =  MessageCodes.SIZE)
    @Pattern(regexp = RegexPatterns.ONLY_BASIC_LETTERS, message = MessageCodes.REGEX_ONLY_BASIC_LETTERS)
    private String name;

    @NotNull(message = MessageCodes.NOT_NULL)
    private Boolean visible;
}
