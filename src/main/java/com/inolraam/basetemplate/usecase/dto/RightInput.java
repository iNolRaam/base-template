package com.inolraam.basetemplate.usecase.dto;

import com.inolraam.basetemplate.adapter.validation.NullOrPositiveId;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RightInput {

    @NullOrPositiveId
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;
}
