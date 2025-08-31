package com.inolraam.basetemplate.usecase.typeright;

import com.inolraam.basetemplate.common.constant.CommonValues;
import com.inolraam.basetemplate.common.constant.Fields;
import com.inolraam.basetemplate.common.exception.NotFoundException;
import com.inolraam.basetemplate.common.exception.RequestValidationException;
import com.inolraam.basetemplate.common.exception.ResourceInUseException;
import com.inolraam.basetemplate.common.exception.dto.InvalidFieldsDto;
import com.inolraam.basetemplate.domain.port.RightRepository;
import com.inolraam.basetemplate.domain.port.TypeRightRepository;
import com.inolraam.basetemplate.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeleteTypeRightUseCase implements UseCase<Long, Void> {

    private final TypeRightRepository typeRightRep;
    private final RightRepository rightRep;

    @Override
    public Void execute(Long id) {
        isAllowedToDelete(id);
        typeRightRep.deleteById(id);
        return null;
    }

    private void isAllowedToDelete(long id){
        if(id < CommonValues.MIN_VALUE_TO_ID){
            throw new  RequestValidationException(new InvalidFieldsDto(Fields.ID, CommonValues.POSITIVE));
        }

        if(!typeRightRep.existsById(id)){
            throw new NotFoundException(id);
        }

        if(rightRep.existsByIdTypeRight(id)){
            throw new ResourceInUseException(id);
        }
    }
}
