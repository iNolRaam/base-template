package com.inolraam.basetemplate.adapter.in;

import com.inolraam.basetemplate.adapter.in.constant.ApiPaths;
import com.inolraam.basetemplate.adapter.in.response.*;
import com.inolraam.basetemplate.adapter.in.swagger.ProfileSwagger;
import com.inolraam.basetemplate.common.exception.RequestValidationException;
import com.inolraam.basetemplate.usecase.UseCaseVoid;
import com.inolraam.basetemplate.usecase.profile.dto.*;
import com.inolraam.basetemplate.usecase.UseCase;
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
    private final UseCase<ProfileInput, ProfileOutput> createProfileUseCase;
    private final UseCaseVoid<ProfileIDInput> deleteProfileUseCase;
    private final UseCase<UpdateProfileInput, ProfileOutput> updateProfileUseCase;
    private final UseCase<Long, ProfileOutput> readProfileUseCase;

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