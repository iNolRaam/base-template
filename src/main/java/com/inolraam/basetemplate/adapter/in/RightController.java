package com.inolraam.basetemplate.adapter.in;

import com.inolraam.basetemplate.adapter.in.constant.ApiPaths;
import com.inolraam.basetemplate.adapter.in.response.Response;
import com.inolraam.basetemplate.adapter.in.response.ResponseBuilder;
import com.inolraam.basetemplate.adapter.in.swagger.RightSwagger;
import com.inolraam.basetemplate.common.exception.RequestValidationException;
import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.UseCaseVoid;
import com.inolraam.basetemplate.usecase.right.dto.RightInput;
import com.inolraam.basetemplate.usecase.right.dto.RightOutput;
import com.inolraam.basetemplate.usecase.right.dto.UpdateRightInput;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightOutput;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.RIGHTS)
public class RightController implements RightSwagger {
    private final UseCase<RightInput, RightOutput> createRightUseCase;
    private final UseCaseVoid<Long> deleteRightUseCase;
    private final UseCase<UpdateRightInput, RightOutput> updateRightUseCase;
    private final UseCase<Long, RightOutput> readRightUseCase;

    @Override
    @PostMapping
    public ResponseEntity<Response> createRight(@Valid @RequestBody RightInput input, BindingResult result) {
        if (result.hasErrors())
            throw new RequestValidationException(result);

        final RightOutput output = createRightUseCase.execute(input);
        return ResponseBuilder.success(HttpStatus.CREATED, output);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteRight(@PathVariable long id) {
        deleteRightUseCase.execute(id);
        return ResponseBuilder.success(HttpStatus.NO_CONTENT);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateRight(@PathVariable long id, @Valid @RequestBody RightInput input,
            BindingResult result) {
        if (result.hasErrors())
            throw new RequestValidationException(result);

        final RightOutput output = updateRightUseCase.execute(new UpdateRightInput(id, input));
        return ResponseBuilder.success(HttpStatus.OK, output);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Response> readRight(@PathVariable long id) {
        final RightOutput output = readRightUseCase.execute(id);
        return ResponseBuilder.success(HttpStatus.OK, output);
    }

}
