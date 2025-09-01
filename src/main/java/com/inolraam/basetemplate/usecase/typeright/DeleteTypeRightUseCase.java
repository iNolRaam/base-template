package com.inolraam.basetemplate.usecase.typeright;

import com.inolraam.basetemplate.domain.port.TypeRightRepository;
import com.inolraam.basetemplate.domain.service.GlobalValidator;
import com.inolraam.basetemplate.domain.service.TypeRightValidator;
import com.inolraam.basetemplate.usecase.UseCaseVoid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteTypeRightUseCase implements UseCaseVoid<Long> {

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
