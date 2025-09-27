package com.inolraam.basetemplate.adapter.in.validation;

public final class MessageCodes {
    private MessageCodes() {
    }
    public static final String PREFIX_VALIDATION_FIELD =  "validation.field.";


    public static final String REQUIRED_FIELD = "object.field.required";
    public static final String DUPLICATED_FIELD = "object.field.duplicated";
    public static final String REQUEST_VALIDATION = "exception.requestValidation";
    public static final String INVALID_FORMAT = "exception.invalidFormat";
    public static final String DEFAULT_INVALID_FORMAT = "exception.default.invalidFormat";
    public static final String NOT_FOUND = "exception.notFound";
    public static final String RESOURCE_IN_USE = "exception.resourceInUse";
    public static final String TYPE_MISMATCH = "exception.typeMismatch";
    public static final String USER_PROFILES_REQUIRED = "validation.field.UserProfilesRequired";



    // Only messages for any annotation need it braces { }
    public static final String NOT_NULL= "{validation.field.NotNull}";
    public static final String NOT_BLANK= "{validation.field.NotBlank}";
    public static final String SIZE = "{validation.field.Size}";
    public static final String POSITIVE = "{validation.field.Positive}";
    public static final String EMAIL = "{validation.field.Email}";

    //Messages for @Pattern annotation
    public static final String REGEX_ONLY_BASIC_LETTERS = "{regex.basicLetters}";
    public static final String REGEX_ONLY_FOR_SYSTEM_PARAMETERS =  "{regex.systemParameter}";
    public static final String REGEX_USERNAME_FORMAT = "{regex.usernameFormat}";

 
}
