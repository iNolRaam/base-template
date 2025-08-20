package com.inolraam.basetemplate.adapter.in.validation;

public final class MessageCodes {
    private MessageCodes() {
    }

    public static final String REQUIRED_FIELD = "object.field.required";
    public static final String DUPLICATED_FIELD = "object.field.duplicated";
    public static final String REQUEST_VALIDATION = "exception.requestValidation";
    public static final String INVALID_FORMAT = "exception.invalidFormat";
    public static final String DEFAULT_INVALID_FORMAT = "exception.default.invalidFormat";

    // Only messages for any annotation need it braces { }
    public static final String NOT_NULL= "{validation.field.NotNull}";
    public static final String NOT_BLANK= "{validation.field.NotBlank}";
    public static final String SIZE = "{validation.field.Size}";

    //Messages for @Pattern annotation
    public static final String REGEX_ONLY_BASIC_LETTERS = "{regex.basicLetters}";

 
}
