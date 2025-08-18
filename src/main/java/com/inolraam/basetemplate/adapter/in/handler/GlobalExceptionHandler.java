package com.inolraam.basetemplate.adapter.in.handler;

import com.inolraam.basetemplate.adapter.in.response.Response;
import com.inolraam.basetemplate.adapter.in.response.ResponseBuilder;
import com.inolraam.basetemplate.common.constant.MessageCodes;
import com.inolraam.basetemplate.common.exception.DuplicatedFieldException;
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

    private final MessageUtil messageUtil;

    @ExceptionHandler(RequiredFieldException.class)
    public ResponseEntity<Response> handelRequiredField(RequiredFieldException ex) {
        final Object [] field = new Object []{ex.getFieldName()};
        return ResponseBuilder.error(messageUtil.getMessage(MessageCodes.REQUIRED_FIELD, field));
    }

    @ExceptionHandler(DuplicatedFieldException.class)
    public ResponseEntity<Response> handelRequiredField(DuplicatedFieldException ex) {
        final Object [] fields = new Object []{ex.getValue(), ex.getFieldName()};
        return ResponseBuilder.error(messageUtil.getMessage(MessageCodes.DUPLICATED_FIELD, fields));
    }

    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<Response> handelRequestValidation(RequestValidationException ex) {
        return ResponseBuilder.error(
                messageUtil.getMessage(MessageCodes.REQUEST_VALIDATION),
                ex.getInvalidFields()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response> handleInvalidFormat(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        ResponseEntity<Response> response;
        if (cause instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException ife) {
            final String field = ife.getPath().get(0).getFieldName();
            final String value = String.valueOf(ife.getValue());
            final Class<?> expectedType = ife.getTargetType();
            final String msgProperties = String.format(messageUtil.getMessage(MessageCodes.INVALID_FORMAT), value, field, expectedType.getSimpleName());
            response = ResponseBuilder.error(msgProperties);
        } else {
            response = ResponseBuilder.error(messageUtil.getMessage(MessageCodes.INVALID_FORMAT));
        }
        return response;
    }
}
