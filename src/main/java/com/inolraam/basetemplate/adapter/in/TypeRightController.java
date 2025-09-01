package com.inolraam.basetemplate.adapter.in;

import com.inolraam.basetemplate.adapter.in.response.*;
import com.inolraam.basetemplate.adapter.in.swagger.TypeRightSwagger;
import com.inolraam.basetemplate.common.exception.RequestValidationException;
import com.inolraam.basetemplate.usecase.UseCaseVoid;
import com.inolraam.basetemplate.usecase.typeright.dto.*;
import com.inolraam.basetemplate.usecase.UseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/type-rights")
public class TypeRightController implements TypeRightSwagger {
    private final UseCase<TypeRightInput, TypeRightOutput> createTypeRightUseCase;
    private final UseCaseVoid<Long> deleteTypeRightUseCase;
    private final UseCase<UpdateTypeRightInput, TypeRightOutput> updateTypeRightUseCase;
    private final UseCase<Long, TypeRightOutput> readTypeRightUseCase;

    @PostMapping
    public ResponseEntity<Response> createTypeRight(@Valid @RequestBody TypeRightInput input, BindingResult result) {
        if (result.hasErrors()) throw new RequestValidationException(result);

        final TypeRightOutput output = createTypeRightUseCase.execute(input);
        return ResponseBuilder.success(HttpStatus.CREATED, output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteTypeRight(@PathVariable long id) {
        deleteTypeRightUseCase.execute(id);
        return ResponseBuilder.success(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateTypeRight(@PathVariable long id, @Valid @RequestBody TypeRightInput input, BindingResult result) {
        if (result.hasErrors()) throw new RequestValidationException(result);

        final TypeRightOutput output = updateTypeRightUseCase.execute(new UpdateTypeRightInput(id, input));
        return ResponseBuilder.success(HttpStatus.OK, output);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> readTypeRight(@PathVariable long id) {
        final TypeRightOutput output = readTypeRightUseCase.execute(id);
        return ResponseBuilder.success(HttpStatus.OK, output);
    }
}
