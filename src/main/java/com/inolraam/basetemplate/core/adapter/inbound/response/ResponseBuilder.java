package com.inolraam.basetemplate.core.adapter.inbound.response;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.inolraam.basetemplate.core.adapter.inbound.response.dto.ErrorResponse;
import com.inolraam.basetemplate.core.adapter.inbound.response.dto.SuccessResponse;

public final class ResponseBuilder {
    private ResponseBuilder() {}

    public static ResponseEntity<Response> error(HttpStatus httpStatus, String error) {
        final Response errorResp = buildErrorResponse(error);
        return createResponseEntity(httpStatus, errorResp);
    }

    public static ResponseEntity<Response> error(HttpStatus httpStatus, String generalError, Serializable[] fieldsWithErrors) {
        final Response errorResp = buildErrorResponse(generalError, fieldsWithErrors);
        return createResponseEntity(httpStatus, errorResp);
    }

    public static ResponseEntity<Response> success(HttpStatus httpStatus, Serializable data) {
        final Response successResp = buildSuccesResponse(data);
        return createResponseEntity(httpStatus, successResp);
    }

    public static ResponseEntity<Response> success(HttpStatus httpStatus) {
        return createResponseEntity(httpStatus, null);
    }

    private static Response buildSuccesResponse(Serializable data) {
        return SuccessResponse.builder().data(data).build();
    }

    private static ResponseEntity<Response> createResponseEntity(HttpStatus httpStatus, Response generalResponse) {
        return ResponseEntity.status(httpStatus).body(generalResponse);
    }

    private static Response buildErrorResponse(String error) {
        return ErrorResponse.builder().errorMessage(error).build();
    }

    private static Response buildErrorResponse(String generalError, Serializable[] fieldsWithErrors) {
        return ErrorResponse.builder()
                .errorMessage(generalError)
                .invalidFields(fieldsWithErrors)
                .build();
    }
}
