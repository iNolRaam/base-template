package com.inolraam.basetemplate.core.usecase.role.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

import com.inolraam.basetemplate.core.usecase.dtoglobal.BaseSystemCatalogDto;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleInput extends BaseSystemCatalogDto {

    private Set<Long> rights;
}