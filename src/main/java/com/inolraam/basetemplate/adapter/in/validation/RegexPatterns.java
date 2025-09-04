package com.inolraam.basetemplate.adapter.in.validation;

public final class RegexPatterns {
    private RegexPatterns() {}

    public static final String ONLY_BASIC_LETTERS = "^[a-zA-Z]+$";
    public static final String ONLY_FOR_SYSTEM_PARAMETERS = "^[a-zA-Z]+(?:_[a-zA-Z]+)*$";
}