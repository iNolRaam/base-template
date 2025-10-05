package com.inolraam.basetemplate.core.adapter.inbound.web.controller;

import com.inolraam.basetemplate.core.adapter.inbound.constant.ApiPaths;
import com.inolraam.basetemplate.core.adapter.inbound.response.*;
import com.inolraam.basetemplate.core.adapter.inbound.swagger.UserSwagger;
import com.inolraam.basetemplate.core.domain.port.inbound.user.CreateUserUseCase;
import com.inolraam.basetemplate.core.domain.port.inbound.user.DeleteUserUseCase;
import com.inolraam.basetemplate.core.domain.port.inbound.user.ReadUserUseCase;
import com.inolraam.basetemplate.core.domain.port.inbound.user.UpdateUserUseCase;
import com.inolraam.basetemplate.core.usecase.user.dto.*;
import com.inolraam.basetemplate.shared.common.exception.RequestValidationException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for User CRUD operations.
 * 
 * @author Generated
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.USERS)
public class UserController implements UserSwagger {
    
    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final ReadUserUseCase readUserUseCase;

    @PostMapping
    @Override
    public ResponseEntity<Response> createUser(@Valid @RequestBody UserInput input, BindingResult result) {
        if (result.hasErrors()) {
            throw new RequestValidationException(result);
        }

        final UserOutput output = createUserUseCase.execute(input);
        return ResponseBuilder.success(HttpStatus.CREATED, output);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Response> deleteUser(@PathVariable long id) {
        deleteUserUseCase.execute(id);
        return ResponseBuilder.success(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Response> updateUser(@PathVariable long id, @Valid @RequestBody UserInput input,
            BindingResult result) {
        if (result.hasErrors()) {
            throw new RequestValidationException(result);
        }

        final UserOutput output = updateUserUseCase.execute(new UpdateUserInput(id, input));
        return ResponseBuilder.success(HttpStatus.OK, output);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Response> readUser(@PathVariable long id) {
        final UserOutput output = readUserUseCase.execute(id);
        return ResponseBuilder.success(HttpStatus.OK, output);
    }
}