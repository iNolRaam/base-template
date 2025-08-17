package com.inolraam.basetemplate.adapter.in;

import com.inolraam.basetemplate.adapter.in.dto.GeneralResponse;
import com.inolraam.basetemplate.common.exception.RequestValidationException;
import com.inolraam.basetemplate.usecase.typeright.dto.CreateTypeRightInput;
import com.inolraam.basetemplate.adapter.in.util.SuccessRespUtil;
import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightOutput;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/type-rights")
public class TypeRightController {
    private final UseCase<CreateTypeRightInput, TypeRightOutput> createTypeRightUseCase;

    @PostMapping
    public ResponseEntity<GeneralResponse> createTypeRight(@Valid @RequestBody CreateTypeRightInput input, BindingResult result) {
        if (result.hasErrors()) throw new RequestValidationException(result);

        final TypeRightOutput output = createTypeRightUseCase.execute(input);
        return SuccessRespUtil.createSuccessResponse(HttpStatus.CREATED, output);
    }

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.status(HttpStatus.CREATED).body("valor");
    }
}
