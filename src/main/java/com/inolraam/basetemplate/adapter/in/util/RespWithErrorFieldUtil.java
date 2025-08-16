package com.inolraam.basetemplate.adapter.in.util;

import com.inolraam.basetemplate.adapter.in.dto.ResponseOut;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;


public final class RespWithErrorFieldUtil {

    private RespWithErrorFieldUtil(){}

    public static ResponseEntity<ResponseOut> createErrorResponse(BindingResult bindingResult) {
        final ResponseOut responseOut = buildResponseOut(getErrors(bindingResult));
        return createResponseEntity(responseOut);
    }

    private static Map<String, String> getErrors(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach((error) -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;
    }

    private static ResponseOut buildResponseOut(Map<String, String> errors) {
        return ResponseOut.builder().errorMessage("There are/is fields with error(s)").fields(errors).build();
    }

    private static ResponseEntity<ResponseOut> createResponseEntity(ResponseOut responseOut) {
        return ResponseEntity.badRequest().body(responseOut);
    }
}
