package ge.economy.involve.security;

/**
 * Created by nino on 5/18/16.
 */
public enum ErrorCodes {

    UNAUTHORIZED(701, "Unauthorized access"),
    INCORRECT_CREDENTIALS(702, "Invalid user credentials"),
    ACCESS_DENIED(703, "Access Denied");

    private int code;
    private String message;

    ErrorCodes(int code) {
        this.code = code;
    }

    ErrorCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
