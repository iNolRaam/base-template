package com.inolraam.basetemplate.core.adapter.inbound.web.controller;

import com.inolraam.basetemplate.core.adapter.inbound.constant.ApiPaths;
import com.inolraam.basetemplate.core.adapter.inbound.response.*;
import com.inolraam.basetemplate.core.adapter.inbound.swagger.TypeRightSwagger;
import com.inolraam.basetemplate.core.domain.port.inbound.typeright.CreateTypeRightUseCase;
import com.inolraam.basetemplate.core.domain.port.inbound.typeright.DeleteTypeRightUseCase;
import com.inolraam.basetemplate.core.domain.port.inbound.typeright.ReadTypeRightUseCase;
import com.inolraam.basetemplate.core.domain.port.inbound.typeright.UpdateTypeRightUseCase;
import com.inolraam.basetemplate.core.usecase.typeright.dto.*;
import com.inolraam.basetemplate.shared.common.exception.RequestValidationException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.TYPE_RIGHTS)
public class TypeRightController implements TypeRightSwagger {
    private final CreateTypeRightUseCase createTypeRightUseCase;
    private final DeleteTypeRightUseCase deleteTypeRightUseCase;
    private final UpdateTypeRightUseCase updateTypeRightUseCase;
    private final ReadTypeRightUseCase readTypeRightUseCase;

    @PostMapping
    public ResponseEntity<Response> createTypeRight(@Valid @RequestBody TypeRightInput input, BindingResult result) {
        if (result.hasErrors())
            throw new RequestValidationException(result);

        final TypeRightOutput output = createTypeRightUseCase.execute(input);
        return ResponseBuilder.success(HttpStatus.CREATED, output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteTypeRight(@PathVariable long id) {
        deleteTypeRightUseCase.execute(id);
        return ResponseBuilder.success(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateTypeRight(@PathVariable long id, @Valid @RequestBody TypeRightInput input,
            BindingResult result) {
        if (result.hasErrors())
            throw new RequestValidationException(result);

        final TypeRightOutput output = updateTypeRightUseCase.execute(new UpdateTypeRightInput(id, input));
        return ResponseBuilder.success(HttpStatus.OK, output);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> readTypeRight(@PathVariable long id) {
        final TypeRightOutput output = readTypeRightUseCase.execute(id);
        return ResponseBuilder.success(HttpStatus.OK, output);
    }
}
