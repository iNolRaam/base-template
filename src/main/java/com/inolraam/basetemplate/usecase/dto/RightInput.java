package com.inolraam.basetemplate.usecase.dto;

import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RightInput extends BaseCatalogDto {

    @Positive
    private long idTypoRight;
}
