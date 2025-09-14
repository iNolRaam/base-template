package com.inolraam.basetemplate.usecase.right;

import com.inolraam.basetemplate.domain.Right;
import com.inolraam.basetemplate.domain.port.RightRepository;
import com.inolraam.basetemplate.domain.service.GlobalValidator;
import com.inolraam.basetemplate.domain.service.RightValidator;
import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.right.dto.UpdateRightInput;
import com.inolraam.basetemplate.usecase.right.dto.RightOutput;
import com.inolraam.basetemplate.usecase.right.mapper.RightDomainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateRightUseCase implements UseCase<UpdateRightInput, RightOutput> {
    private final RightRepository rightRepository;
    private final RightValidator rightValidator;

    @Override
    @Transactional
    public RightOutput execute(UpdateRightInput input) {
        Right newData = RightDomainMapper.toDomain(input.id(), input.RightInput());
        validateUpdatingAllowed(newData);
        Right original = rightRepository.findById(newData.getId());
        Right persisted = rightRepository.save(original.updateWith(newData));
        return RightDomainMapper.toOutput(persisted);
    }

    private void validateUpdatingAllowed(Right right) {
        GlobalValidator.validateIdIsPositive(right.getId());
        rightValidator.validateRightExists(right.getId());
        rightValidator.validateNameIsUniqueExcludingId(right.getId(), right.getName());
    }
}
