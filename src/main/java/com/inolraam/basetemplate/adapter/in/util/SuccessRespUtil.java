package com.inolraam.basetemplate.adapter.in.util;

import com.inolraam.basetemplate.adapter.in.dto.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class SuccessRespUtil {

    private SuccessRespUtil() {}

    public static ResponseEntity<GeneralResponse> createSuccessResponse(HttpStatus httpStatus, Object data) {
        final GeneralResponse generalResponse = buildResponseOut(data);
        return createResponseEntity(httpStatus, generalResponse);
    }

    private static GeneralResponse buildResponseOut(Object data) {
        return GeneralResponse.builder().data(data).build();
    }

    private static ResponseEntity<GeneralResponse> createResponseEntity(HttpStatus httpStatus, GeneralResponse generalResponse) {
        return ResponseEntity.status(httpStatus).body(generalResponse);
    }
}
