package com.inolraam.basetemplate.usecase.typeright;

import com.inolraam.basetemplate.common.exception.RequiredFieldException;
import com.inolraam.basetemplate.domain.port.TypeRightRepository;
import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.typeright.dto.CreateTypeRightInput;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightOutput;
import com.inolraam.basetemplate.usecase.typeright.dto.UpdateTypeRightInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UpdateTypeRightUseCase implements UseCase <UpdateTypeRightInput, TypeRightOutput>{

    private final TypeRightRepository typeRightRepository;

    @Override
    public TypeRightOutput execute(UpdateTypeRightInput input) {

    return null;
    }

    private void isValid(UpdateTypeRightInput input) {
        if (!StringUtils.hasText(input.getName()))
            throw new RequiredFieldException("es");
    }
}
