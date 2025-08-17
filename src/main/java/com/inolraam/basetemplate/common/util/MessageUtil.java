package com.inolraam.basetemplate.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public final class MessageUtil {

    private final MessageSource messageSource;

    public String getMessage(String message){
        return messageSource.getMessage(message, null, Locale.getDefault());
    }

    public String getMessage(String message, Object[] args){
        return messageSource.getMessage(message, args, Locale.getDefault());
    }
}
