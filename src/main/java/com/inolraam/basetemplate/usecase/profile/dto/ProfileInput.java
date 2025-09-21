package com.inolraam.basetemplate.usecase.profile.dto;

import com.inolraam.basetemplate.adapter.in.validation.MessageCodes;
import com.inolraam.basetemplate.usecase.dtoglobal.BaseSystemCatalogDto;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProfileInput extends BaseSystemCatalogDto {

    @NotNull(message = MessageCodes.NOT_NULL)
    private Set<Long> roles;
}