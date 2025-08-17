package com.inolraam.basetemplate.usecase.right.dto;

import com.inolraam.basetemplate.usecase.dto.BaseCatalogDto;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateRightInput extends BaseCatalogDto {
    @Positive
    private long idTypoRight;
}
