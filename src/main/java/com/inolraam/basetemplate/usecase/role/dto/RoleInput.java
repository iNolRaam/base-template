package com.inolraam.basetemplate.usecase.role.dto;

import com.inolraam.basetemplate.usecase.dtoglobal.BaseSystemCatalogDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleInput extends BaseSystemCatalogDto {

    private Set<Long> rights;
}