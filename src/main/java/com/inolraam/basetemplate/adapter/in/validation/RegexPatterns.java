package com.inolraam.basetemplate.adapter.in.validation;

public final class RegexPatterns {
    private RegexPatterns() {}

    public static final String ONLY_BASIC_LETTERS = "^[a-zA-Z]+$";
    public static final String ONLY_FOR_SYSTEM_PARAMETERS = "^[a-zA-Z]+(?:_[a-zA-Z]+)*$";
    public static final String USERNAME_FORMAT = "^[a-zA-Z0-9]+(?:[_.]{1,2}[a-zA-Z0-9]+)*$";
}