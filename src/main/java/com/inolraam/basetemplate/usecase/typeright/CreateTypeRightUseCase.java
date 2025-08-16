package com.inolraam.basetemplate.usecase.typeright;

import com.inolraam.basetemplate.domain.TypeRight;
import com.inolraam.basetemplate.domain.port.TypeRightRepository;
import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.dto.TypeRightInput;
import com.inolraam.basetemplate.usecase.dto.TypeRightOutput;
import com.inolraam.basetemplate.usecase.typeright.mapper.TypeRightDtoMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CreateTypeRightUseCase implements UseCase<TypeRightInput, TypeRightOutput> {

    private final TypeRightRepository typeRightRep;

    @Override
    @Transactional
    public TypeRightOutput execute(TypeRightInput input) {
        if(!StringUtils.hasText(input.getName())) throw new IllegalArgumentException("name required");
        TypeRight right = TypeRightDtoMapper.toDomain(input);
        TypeRight saved = typeRightRep.save(right);
        return TypeRightDtoMapper.toOutput(saved);
    }
}
