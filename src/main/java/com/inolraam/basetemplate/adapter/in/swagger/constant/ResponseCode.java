package com.inolraam.basetemplate.adapter.in.swagger.constant;

public final class ResponseCode {
    private static final String BASE_REF = "#/components/responses/";
    public static final String OK_200 = "200";
    public static final String CREATED_201 = "201";
    public static final String NO_CONTENT_204 = "204";

    public static final String BAD_REQUEST_400 = "400";
    public static final String REF_BAD_REQUEST_400 = BASE_REF + BAD_REQUEST_400;

    public static final String NOT_FOUND_404 = "404";
    public static final String REF_NOT_FOUND_404 = BASE_REF + NOT_FOUND_404;

    private ResponseCode() {}
}
