package com.inolraam.basetemplate.usecase.dto;

import com.inolraam.basetemplate.adapter.in.validation.MustBeBoolean;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

@Data
@SuperBuilder
@NoArgsConstructor
public class BaseCatalogDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "{validation.field.NotBlank}")
    @Size(min = 3, max = 100, message = "{validation.field.Size}")
    private String name;

    @NotNull
    private Boolean visible;
}
