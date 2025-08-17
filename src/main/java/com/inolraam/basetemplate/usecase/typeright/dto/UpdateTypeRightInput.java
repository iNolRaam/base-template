package com.inolraam.basetemplate.usecase.typeright.dto;

import com.inolraam.basetemplate.usecase.dto.BaseCatalogDto;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateTypeRightInput extends BaseCatalogDto {
    @Positive
    private long id;
}
