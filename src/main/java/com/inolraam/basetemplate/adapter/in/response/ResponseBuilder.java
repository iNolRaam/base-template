package com.inolraam.basetemplate.adapter.in.response;

import com.inolraam.basetemplate.adapter.in.response.dto.ErrorResponse;
import com.inolraam.basetemplate.adapter.in.response.dto.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Objects;

public final class ResponseBuilder {
    private ResponseBuilder() {}

    public static ResponseEntity<Response> error(HttpStatus httpStatus, String error) {
        final Response errorResp = buildErrorResponse(error);
        return createResponseEntity(httpStatus, errorResp);
    }

    public static ResponseEntity<Response> error(HttpStatus httpStatus, String generalError, Object[] fieldsWithErrors) {
        final Response errorResp = buildErrorResponse(generalError, fieldsWithErrors);
        return createResponseEntity(httpStatus, errorResp);
    }

    public static ResponseEntity<Response> success(HttpStatus httpStatus, Object data) {
        final Response successResp = buildSuccesResponse(data);
        return createResponseEntity(httpStatus, successResp);
    }

    public static ResponseEntity<Response> success(HttpStatus httpStatus) {
        return createResponseEntity(httpStatus, null);
    }

    private static Response buildSuccesResponse(Object data) {
        return SuccessResponse.builder().data(data).build();
    }

    private static ResponseEntity<Response> createResponseEntity(HttpStatus httpStatus, Response generalResponse) {
        return ResponseEntity.status(httpStatus).body(generalResponse);
    }

    private static Response buildErrorResponse(String error) {
        return ErrorResponse.builder().errorMessage(error).build();
    }

    private static Response buildErrorResponse(String generalError, Object[] fieldsWithErrors) {
        return ErrorResponse.builder()
                .errorMessage(generalError)
                .InvalidFields(fieldsWithErrors)
                .build();
    }
}
