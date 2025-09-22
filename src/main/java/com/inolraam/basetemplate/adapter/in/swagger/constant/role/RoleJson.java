package com.inolraam.basetemplate.adapter.in.swagger.constant.role;

public final class RoleJson {
    private RoleJson() {}
    
    public static final String REQUEST_EXAMPLE = """
        {
            "name": "Manager",
            "rights": [1, 2, 3]
        }
        """;
        
    public static final String RESPONSE_EXAMPLE = """
        {
            "success": true,
            "data": {
                "id": 1,
                "name": "Manager",
                "rights": [1, 2, 3],
                "createdDate": "2024-01-15T10:30:00",
                "lastModifiedDate": "2024-01-15T10:30:00"
            },
            "errors": null
        }
        """;
}