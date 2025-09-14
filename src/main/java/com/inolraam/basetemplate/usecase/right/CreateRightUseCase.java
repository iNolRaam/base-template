package com.inolraam.basetemplate.usecase.right;

import com.inolraam.basetemplate.domain.Right;
import com.inolraam.basetemplate.domain.port.RightRepository;
import com.inolraam.basetemplate.domain.service.RightValidator;
import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.right.dto.RightInput;
import com.inolraam.basetemplate.usecase.right.dto.RightOutput;
import com.inolraam.basetemplate.usecase.right.mapper.RightDomainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateRightUseCase implements UseCase<RightInput, RightOutput> {
    private final RightRepository rightRepository;
    private final RightValidator rightValidator;

    @Override
    @Transactional
    public RightOutput execute(RightInput input) {
        Right right = RightDomainMapper.toDomain(input);
        validateCreatingAllowed(right);
        Right saved = rightRepository.save(right);
        return RightDomainMapper.toOutput(saved);
    }

    private void validateCreatingAllowed(Right input) {
        rightValidator.validateNameIsUnique(input.getName());
    }
}
