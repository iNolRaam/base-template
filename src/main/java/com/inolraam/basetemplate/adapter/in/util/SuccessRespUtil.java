package com.inolraam.basetemplate.adapter.in.util;

import com.inolraam.basetemplate.adapter.in.dto.ResponseOut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public final class SuccessRespUtil {

    private SuccessRespUtil() {}

    public static ResponseEntity<ResponseOut> createSuccessResponse(HttpStatus httpStatus, Object data) {
        final ResponseOut responseOut = buildResponseOut(data);
        return createResponseEntity(httpStatus, responseOut);
    }

    private static ResponseOut buildResponseOut(Object data) {
        return ResponseOut.builder().data(data).build();
    }

    private static ResponseEntity<ResponseOut> createResponseEntity(HttpStatus httpStatus, ResponseOut responseOut) {
        return ResponseEntity.status(httpStatus).body(responseOut);
    }
}
