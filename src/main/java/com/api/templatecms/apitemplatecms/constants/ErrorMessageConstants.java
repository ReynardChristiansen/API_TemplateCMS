package com.api.templatecms.apitemplatecms.constants;

public class ErrorMessageConstants {
    private ErrorMessageConstants(){
        throw   new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    public static final String GENERAL_SUCCESS = "Success";
    public static final String FORBIDDEN_REQ = "Please Check Detail field in object errors";
    public static final String UNAUTHORIZED = "Unauthorized Access";
}
