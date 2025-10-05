package com.inolraam.basetemplate.core.domain.service;

import com.inolraam.basetemplate.shared.common.constant.Fields;
import com.inolraam.basetemplate.shared.common.constant.Global;
import com.inolraam.basetemplate.shared.common.exception.RequestValidationException;
import com.inolraam.basetemplate.shared.common.exception.dto.InvalidFieldsDto;

public final class GlobalValidator {
    private GlobalValidator() {}

    public static void validateIdIsPositive(Long id) {
        if (id == null || id < Global.MIN_VALUE_TO_ID) {
            throw new RequestValidationException(
                    new InvalidFieldsDto(Fields.ID, Global.POSITIVE)
            );
        }
    }
}
