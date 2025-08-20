package com.inolraam.basetemplate.adapter.in.response;

import com.inolraam.basetemplate.adapter.in.response.dto.ErrorResponse;
import com.inolraam.basetemplate.adapter.in.response.dto.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Objects;

public final class ResponseBuilder {
    private ResponseBuilder() {}

    public static ResponseEntity<Response> error(String error) {
        final Response errorResp = buildErrorResponse(error);
        return createResponseEntity(errorResp);
    }

    public static ResponseEntity<Response> error(String generalError, Object[] fieldsWithErrors) {
        final Response errorResp = buildErrorResponse(generalError, fieldsWithErrors);
        return createResponseEntity(errorResp);
    }

    public static ResponseEntity<Response> success(HttpStatus httpStatus, Object data) {
        final Response successResp = buildSuccesResponse(data);
        return createResponseEntity(httpStatus, successResp);
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


    private static ResponseEntity<Response> createResponseEntity(Response response) {
        return ResponseEntity.badRequest().body(response);
    }
}
