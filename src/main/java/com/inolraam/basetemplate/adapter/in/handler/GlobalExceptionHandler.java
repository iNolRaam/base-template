package com.inolraam.basetemplate.adapter.in.handler;

import com.inolraam.basetemplate.adapter.in.response.Response;
import com.inolraam.basetemplate.adapter.in.response.ResponseBuilder;
import com.inolraam.basetemplate.adapter.in.validation.MessageCodes;
import com.inolraam.basetemplate.common.constant.Global;
import com.inolraam.basetemplate.common.exception.*;
import com.inolraam.basetemplate.common.util.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        return ResponseBuilder.error(HttpStatus.BAD_REQUEST, messageUtil.getMessage(MessageCodes.REQUIRED_FIELD, field));
    }

    @ExceptionHandler(DuplicatedFieldException.class)
    public ResponseEntity<Response> handelRequiredField(DuplicatedFieldException ex) {
        final Object [] fields = new Object []{ex.getValue(), ex.getFieldName()};
        return ResponseBuilder.error(HttpStatus.CONFLICT, messageUtil.getMessage(MessageCodes.DUPLICATED_FIELD, fields));
    }

    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<Response> handelRequestValidation(RequestValidationException ex) {
        final String message = messageUtil.getMessage(MessageCodes.REQUEST_VALIDATION);

        return  ex.isManual()
                ? processManual(message, ex.getInvalidFields())
                : ResponseBuilder.error(HttpStatus.BAD_REQUEST, message, ex.getInvalidFields());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handelRequestValidation(NotFoundException ex) {
        final String errorMessage = String.format(messageUtil.getMessage(MessageCodes.NOT_FOUND), ex.getSearchBy(), ex.getValue());
        return ResponseBuilder.error(HttpStatus.NOT_FOUND, errorMessage);
    }

    @ExceptionHandler(ResourceInUseException.class)
    public ResponseEntity<Response> handelRequestValidation(ResourceInUseException ex) {
        final Object [] field = new Object []{ex.getId()};
        return ResponseBuilder.error(HttpStatus.CONFLICT, messageUtil.getMessage(MessageCodes.RESOURCE_IN_USE), field);
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
            response = ResponseBuilder.error(HttpStatus.BAD_REQUEST, msgProperties);
        } else {
            response = ResponseBuilder.error(HttpStatus.BAD_REQUEST, messageUtil.getMessage(MessageCodes.DEFAULT_INVALID_FORMAT));
        }
        return response;
    }


    private ResponseEntity<Response> processManual(String message, Object[] invalidFields){
        final Object[] invalidFieldsWithMessage = new Object[invalidFields.length];
        
        for(int i = 0; i < invalidFields.length; i++){
            System.out.println(invalidFields[i]);
            String[] obj = invalidFields[i].toString().split(Global.EQUAL_SIGN);
            String field = obj[0];
            String value = obj[1];
            invalidFieldsWithMessage[i] = new Object[]{field, messageUtil.getMessage(MessageCodes.PREFIX_VALIDATION_FIELD + value)};
        }
        return ResponseBuilder.error(HttpStatus.BAD_REQUEST, message,  invalidFieldsWithMessage);
    }
}
