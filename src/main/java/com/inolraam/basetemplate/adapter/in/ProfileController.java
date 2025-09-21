package com.inolraam.basetemplate.adapter.in;

import com.inolraam.basetemplate.adapter.in.constant.ApiPaths;
import com.inolraam.basetemplate.adapter.in.response.*;
import com.inolraam.basetemplate.adapter.in.swagger.ProfileSwagger;
import com.inolraam.basetemplate.common.exception.RequestValidationException;
import com.inolraam.basetemplate.usecase.profile.CreateProfileUseCase;
import com.inolraam.basetemplate.usecase.profile.DeleteProfileUseCase;
import com.inolraam.basetemplate.usecase.profile.ReadProfileUseCase;
import com.inolraam.basetemplate.usecase.profile.UpdateProfileUseCase;
import com.inolraam.basetemplate.usecase.profile.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.PROFILES)
public class ProfileController implements ProfileSwagger {
    private final CreateProfileUseCase createProfileUseCase;
    private final DeleteProfileUseCase deleteProfileUseCase;
    private final UpdateProfileUseCase updateProfileUseCase;
    private final ReadProfileUseCase readProfileUseCase;

    @PostMapping
    public ResponseEntity<Response> createProfile(@Valid @RequestBody ProfileInput input, BindingResult result) {
        if (result.hasErrors())
            throw new RequestValidationException(result);

        final ProfileOutput output = createProfileUseCase.execute(input);
        return ResponseBuilder.success(HttpStatus.CREATED, output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteProfile(@PathVariable long id) {
        deleteProfileUseCase.execute(new ProfileIDInput(id));
        return ResponseBuilder.success(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateProfile(@PathVariable long id, @Valid @RequestBody ProfileInput input,
            BindingResult result) {
        if (result.hasErrors())
            throw new RequestValidationException(result);

        final ProfileOutput output = updateProfileUseCase.execute(new UpdateProfileInput(id, input));
        return ResponseBuilder.success(HttpStatus.OK, output);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> readProfile(@PathVariable long id) {
        final ProfileOutput output = readProfileUseCase.execute(id);
        return ResponseBuilder.success(HttpStatus.OK, output);
    }
}