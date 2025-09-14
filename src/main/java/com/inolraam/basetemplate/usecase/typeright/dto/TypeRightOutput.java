package com.inolraam.basetemplate.usecase.typeright.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inolraam.basetemplate.usecase.dtoglobal.BaseSystemCatalogDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeRightOutput  extends BaseSystemCatalogDto {
    private long id;

}
