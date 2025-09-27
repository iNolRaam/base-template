package com.inolraam.basetemplate.adapter.in.swagger.constant.user;

/**
 * JSON examples for User API documentation.
 * 
 * @author Generated
 * @version 1.0
 */
public final class UserJson {
    private UserJson() {}
    
    public static final String REQUEST_EXAMPLE = """
        {
            "email": "john.doe@example.com",
            "username": "johndoe",
            "status": "ACTIVE",
            "profiles": [1, 2]
        }
        """;

    public static final String RESPONSE_EXAMPLE = """
        {
            "id": 1,
            "email": "john.doe@example.com",
            "username": "johndoe",
            "emailVerified": false,
            "status": "ACTIVE",
            "lastLogin": null,
            "profiles": [1, 2]
        }
        """;
}