package com.inolraam.basetemplate.core.usecase.typeright.impl;

import com.inolraam.basetemplate.core.domain.port.inbound.typeright.DeleteTypeRightUseCase;
import com.inolraam.basetemplate.core.domain.port.outbound.TypeRightRepository;
import com.inolraam.basetemplate.core.domain.service.GlobalValidator;
import com.inolraam.basetemplate.core.domain.service.TypeRightValidator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteTypeRightUseCaseImpl implements DeleteTypeRightUseCase {

    private final TypeRightRepository typeRightRep;
    private final TypeRightValidator typeRightValidator;

    @Override
    @Transactional
    public void execute(Long id) {
        validateDeletionAllowed(id);
        typeRightRep.deleteById(id);
    }

    private void validateDeletionAllowed(Long id){
        GlobalValidator.validateIdIsPositive(id);
        typeRightValidator.validateTypeRightExists(id);
        typeRightValidator.validateTypeRightNotInUse(id);
    }
}
