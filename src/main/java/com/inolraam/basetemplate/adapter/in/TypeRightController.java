package com.inolraam.basetemplate.adapter.in;

import com.inolraam.basetemplate.adapter.in.dto.ResponseOut;
import com.inolraam.basetemplate.adapter.in.util.RespWithErrorFieldUtil;
import com.inolraam.basetemplate.adapter.in.util.SuccessRespUtil;
import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.dto.TypeRightInput;
import com.inolraam.basetemplate.usecase.dto.TypeRightOutput;
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
    private final UseCase<TypeRightInput, TypeRightOutput> createTypeRightUseCase;

    @PostMapping
    public ResponseEntity<ResponseOut> createTypeRight(@Valid @RequestBody TypeRightInput input, BindingResult result) {
        if (result.hasErrors()) return RespWithErrorFieldUtil.createErrorResponse(result);

        final TypeRightOutput output = createTypeRightUseCase.execute(input);
        return SuccessRespUtil.createSuccessResponse(HttpStatus.CREATED, output);
    }

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.status(HttpStatus.CREATED).body("valor");
    }
}
