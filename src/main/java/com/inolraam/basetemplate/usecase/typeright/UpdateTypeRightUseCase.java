package com.inolraam.basetemplate.usecase.typeright;

import com.inolraam.basetemplate.domain.TypeRight;
import com.inolraam.basetemplate.domain.port.TypeRightRepository;
import com.inolraam.basetemplate.domain.service.GlobalValidator;
import com.inolraam.basetemplate.domain.service.TypeRightValidator;
import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightOutput;
import com.inolraam.basetemplate.usecase.typeright.dto.UpdateTypeRightInput;
import com.inolraam.basetemplate.usecase.typeright.mapper.TypeRightDomainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateTypeRightUseCase implements UseCase <UpdateTypeRightInput, TypeRightOutput>{

    private final TypeRightRepository typeRightRepository;
    private final TypeRightValidator typeRightValidator;

    @Override
    @Transactional
    public TypeRightOutput execute(UpdateTypeRightInput input) {
        final TypeRight newData = TypeRightDomainMapper.toDomain(input.id(), input.typeRightInput());
        validateUpdatingAllowed(newData);
        final TypeRight original = typeRightRepository.findById(newData.getId());
        final TypeRight persisted  =  typeRightRepository.update(original.updateWith(newData));
        return TypeRightDomainMapper.toOutput(persisted);
    }

    private void validateUpdatingAllowed(TypeRight typeRight) {
        GlobalValidator.validateIdIsPositive(typeRight.getId());
        typeRightValidator.validateTypeRightExists(typeRight.getId());
        typeRightValidator.validateNameIsUniqueExcludingId(typeRight.getId(), typeRight.getName());
    }
}
