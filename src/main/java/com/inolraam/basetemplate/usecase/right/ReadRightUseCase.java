package com.inolraam.basetemplate.usecase.right;

import com.inolraam.basetemplate.domain.Right;
import com.inolraam.basetemplate.domain.port.RightRepository;
import com.inolraam.basetemplate.domain.service.GlobalValidator;
import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.right.dto.RightOutput;
import com.inolraam.basetemplate.usecase.right.mapper.RightDomainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReadRightUseCase implements UseCase<Long, RightOutput> {
    private final RightRepository rightRepository;

    @Override
    @Transactional(readOnly = true)
    public RightOutput execute(Long id) {
        validateReadingAllowed(id);
        final Right right = rightRepository.findById(id);
        return RightDomainMapper.toOutput(right);
    }

    private void validateReadingAllowed(Long id) {
        GlobalValidator.validateIdIsPositive(id);
    }
}