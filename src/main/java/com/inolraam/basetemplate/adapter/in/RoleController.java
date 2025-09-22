package com.inolraam.basetemplate.adapter.in;

import com.inolraam.basetemplate.adapter.in.constant.ApiPaths;
import com.inolraam.basetemplate.adapter.in.response.*;
import com.inolraam.basetemplate.adapter.in.swagger.RoleSwagger;
import com.inolraam.basetemplate.common.exception.RequestValidationException;
import com.inolraam.basetemplate.usecase.role.CreateRoleUseCase;
import com.inolraam.basetemplate.usecase.role.DeleteRoleUseCase;
import com.inolraam.basetemplate.usecase.role.ReadRoleUseCase;
import com.inolraam.basetemplate.usecase.role.UpdateRoleUseCase;
import com.inolraam.basetemplate.usecase.role.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.ROLES)
public class RoleController implements RoleSwagger {
    private final CreateRoleUseCase createRoleUseCase;
    private final DeleteRoleUseCase deleteRoleUseCase;
    private final UpdateRoleUseCase updateRoleUseCase;
    private final ReadRoleUseCase readRoleUseCase;

    @PostMapping
    public ResponseEntity<Response> createRole(@Valid @RequestBody RoleInput input, BindingResult result) {
        if (result.hasErrors())
            throw new RequestValidationException(result);

        final RoleOutput output = createRoleUseCase.execute(input);
        return ResponseBuilder.success(HttpStatus.CREATED, output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteRole(@PathVariable long id) {
        deleteRoleUseCase.execute(new RoleIDInput(id));
        return ResponseBuilder.success(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateRole(@PathVariable long id, @Valid @RequestBody RoleInput input,
            BindingResult result) {
        if (result.hasErrors())
            throw new RequestValidationException(result);

        final RoleOutput output = updateRoleUseCase.execute(new UpdateRoleInput(id, input));
        return ResponseBuilder.success(HttpStatus.OK, output);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> readRole(@PathVariable long id) {
        final RoleOutput output = readRoleUseCase.execute(id);
        return ResponseBuilder.success(HttpStatus.OK, output);
    }
}