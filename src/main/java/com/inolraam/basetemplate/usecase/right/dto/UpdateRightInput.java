package com.inolraam.basetemplate.usecase.right.dto;

import com.inolraam.basetemplate.usecase.dtoglobal.BaseSystemCatalogDto;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateRightInput extends BaseSystemCatalogDto {
    @Positive
    private long id;

    @Positive
    private long idTypoRight;
}
