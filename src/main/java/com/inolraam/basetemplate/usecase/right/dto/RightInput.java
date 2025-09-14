package com.inolraam.basetemplate.usecase.right.dto;

import com.inolraam.basetemplate.usecase.dtoglobal.BaseSystemCatalogDto;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RightInput extends BaseSystemCatalogDto {
    @Positive
    private long idTypeRight;
}
