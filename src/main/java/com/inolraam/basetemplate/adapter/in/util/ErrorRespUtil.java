package com.inolraam.basetemplate.adapter.in.util;

import com.inolraam.basetemplate.adapter.in.dto.GeneralResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public final class ErrorRespUtil {

    private ErrorRespUtil() {}

    public static ResponseEntity<GeneralResponse> createErrorResponse(String error) {
        final GeneralResponse generalResponse = buildResponseOut(error);
        return createResponseEntity(generalResponse);
    }

    public static ResponseEntity<GeneralResponse> createErrorResponse(String generalError, Map<String, String> fieldsWithErrors) {
        final GeneralResponse generalResponse = buildResponseOut(generalError, fieldsWithErrors);
        return createResponseEntity(generalResponse);
    }

    private static GeneralResponse buildResponseOut(String error) {
        return GeneralResponse.builder().errorMessage(error).build();
    }

    private static GeneralResponse buildResponseOut(String generalError, Map<String, String> fieldsWithErrors) {
        return GeneralResponse.builder()
                .errorMessage(generalError)
                .InvalidFields(fieldsWithErrors)
                .build();
    }


    private static ResponseEntity<GeneralResponse> createResponseEntity(GeneralResponse generalResponse) {
        return ResponseEntity.badRequest().body(generalResponse);
    }
}
