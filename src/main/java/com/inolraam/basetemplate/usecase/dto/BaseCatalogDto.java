package com.inolraam.basetemplate.usecase.dto;

import com.inolraam.basetemplate.adapter.in.validation.NullOrPositiveId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
public class BaseCatalogDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NullOrPositiveId
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    private boolean visible;
}
