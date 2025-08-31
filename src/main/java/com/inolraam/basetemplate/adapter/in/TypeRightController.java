package com.inolraam.basetemplate.adapter.in;

import com.inolraam.basetemplate.adapter.in.response.Response;
import com.inolraam.basetemplate.adapter.in.response.ResponseBuilder;
import com.inolraam.basetemplate.adapter.in.swagger.TypeRightSwagger;
import com.inolraam.basetemplate.adapter.in.validation.MessageCodes;
import com.inolraam.basetemplate.common.exception.RequestValidationException;
import com.inolraam.basetemplate.usecase.typeright.dto.CreateTypeRightInput;
import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightOutput;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/type-rights")
public class TypeRightController implements TypeRightSwagger {
    private final UseCase<CreateTypeRightInput, TypeRightOutput> createTypeRightUseCase;
    private final UseCase<Long, Void> deleteTypeRightUseCase;

    @PostMapping
    public ResponseEntity<Response> createTypeRight(@Valid @RequestBody CreateTypeRightInput input, BindingResult result) {
        if (result.hasErrors()) throw new RequestValidationException(result);

        final TypeRightOutput output = createTypeRightUseCase.execute(input);
        return ResponseBuilder.success(HttpStatus.CREATED, output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteTypeRight(@PathVariable long id) {
        deleteTypeRightUseCase.execute(id);
        return ResponseBuilder.success(HttpStatus.ACCEPTED);
    }
}
