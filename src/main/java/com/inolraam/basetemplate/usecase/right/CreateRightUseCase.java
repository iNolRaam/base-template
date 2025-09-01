package com.inolraam.basetemplate.usecase.right;

import com.inolraam.basetemplate.common.constant.Fields;
import com.inolraam.basetemplate.common.exception.RequiredFieldException;
import com.inolraam.basetemplate.domain.Right;
import com.inolraam.basetemplate.domain.port.RightRepository;
import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.right.dto.RightInput;
import com.inolraam.basetemplate.usecase.right.dto.UpdateRightInput;
import com.inolraam.basetemplate.usecase.right.dto.RightOutput;
import com.inolraam.basetemplate.usecase.right.mapper.RightDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CreateRightUseCase implements UseCase<RightInput, RightOutput> {
    private final RightRepository rightRepository;

    public RightOutput execute(RightInput input) {
        isValid(input);
        Right right = RightDtoMapper.toDomain(input);
        Right saved = rightRepository.save(right);
        return RightDtoMapper.toOutput(saved);
    }

    private void isValid(RightInput input) {

    }
}
