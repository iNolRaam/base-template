package com.inolraam.basetemplate.adapter.in.constant;

public final class ApiPaths {
    private ApiPaths() {}

    private static final String BASE = "/api/v1";

    public static final String ACCESS_MANAGEMENT = BASE + "/access-management";
    public static final String TYPE_RIGHTS = ACCESS_MANAGEMENT + "/type-rights";
    public static final String RIGHTS = ACCESS_MANAGEMENT + "/rights";

    public static final String USERS = BASE + "/users";
}
