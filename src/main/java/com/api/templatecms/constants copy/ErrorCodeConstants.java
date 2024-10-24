package id.bcafinance.template.cms.constants;

public class ErrorCodeConstants {
    private ErrorCodeConstants(){
        throw   new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    public static final String GENERAL_SUCCESS = "GEN-00";
    public static final String GENERAL_ERROR = "GEN-99";
    public static final String UNAUTHORIZED = "401-01";
    public static final String FORBIDDEN_REQ = "400-01";
}
