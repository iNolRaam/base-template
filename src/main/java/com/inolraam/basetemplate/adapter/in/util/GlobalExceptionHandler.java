package com.inolraam.basetemplate.adapter.in.util;

import com.inolraam.basetemplate.adapter.in.dto.GeneralResponse;
import com.inolraam.basetemplate.common.exception.RequestValidationException;
import com.inolraam.basetemplate.common.exception.RequiredFieldException;
import com.inolraam.basetemplate.common.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private static final String MSG_REQUEST_VALIDATION = "exception.requestValidation";
    private static final String MSG_REQUIRED_FIELD = "object.%s.required";
    private static final String MSG_INVALID_FORMAT = "exception.invalidFormat";
    private static final String MSG_DEFAULT_INVALID_FORMAT = "exception.default.invalidFormat";

    private final MessageUtil messageUtil;

    @ExceptionHandler(RequiredFieldException.class)
    public ResponseEntity<GeneralResponse> handelRequiredField(RequiredFieldException ex) {
        final String msgProperties = String.format(MSG_REQUIRED_FIELD, ex.getFieldName());
        return ErrorRespUtil.createErrorResponse(messageUtil.getMessage(msgProperties));
    }

    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<GeneralResponse> handelRequestValidation(RequestValidationException ex) {
        return ErrorRespUtil.createErrorResponse(
                messageUtil.getMessage(MSG_REQUEST_VALIDATION),
                ex.getInvalidFields()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<GeneralResponse> handleInvalidFormat(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        ResponseEntity<GeneralResponse> response;
        if (cause instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException ife) {
            final String field = ife.getPath().get(0).getFieldName();
            final String value = String.valueOf(ife.getValue());
            final Class<?> expectedType = ife.getTargetType();
            final String msgProperties = String.format(messageUtil.getMessage(MSG_INVALID_FORMAT), value, field, expectedType.getSimpleName());
            response = ErrorRespUtil.createErrorResponse(msgProperties);
        } else {
            response = ErrorRespUtil.createErrorResponse(messageUtil.getMessage(MSG_DEFAULT_INVALID_FORMAT));
        }
        return response;
    }
}
