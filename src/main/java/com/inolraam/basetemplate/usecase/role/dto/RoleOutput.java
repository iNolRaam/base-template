package com.inolraam.basetemplate.usecase.role.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inolraam.basetemplate.usecase.dtoglobal.BaseSystemCatalogDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleOutput extends BaseSystemCatalogDto {
    private long id;
    private Set<Long> rights;
}