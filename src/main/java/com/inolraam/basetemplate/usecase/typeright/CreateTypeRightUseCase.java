package com.inolraam.basetemplate.usecase.typeright;

import com.inolraam.basetemplate.common.constant.Fields;
import com.inolraam.basetemplate.common.exception.DuplicatedFieldException;
import com.inolraam.basetemplate.common.exception.RequiredFieldException;
import com.inolraam.basetemplate.domain.TypeRight;
import com.inolraam.basetemplate.domain.port.TypeRightRepository;
import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.typeright.dto.CreateTypeRightInput;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightOutput;
import com.inolraam.basetemplate.usecase.typeright.mapper.TypeRightDtoMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CreateTypeRightUseCase implements UseCase<CreateTypeRightInput, TypeRightOutput> {
    private final TypeRightRepository typeRightRep;

    @Override
    @Transactional
    public TypeRightOutput execute(CreateTypeRightInput input) {
        isValid(input);
        TypeRight right = TypeRightDtoMapper.toDomain(input);
        TypeRight saved = typeRightRep.save(right);
        return TypeRightDtoMapper.toOutput(saved);
    }

    private void isValid(CreateTypeRightInput input) {
        if(typeRightRep.existsByName(input.getName()))
            throw new DuplicatedFieldException(Fields.NAME, input.getName());
    }
}
