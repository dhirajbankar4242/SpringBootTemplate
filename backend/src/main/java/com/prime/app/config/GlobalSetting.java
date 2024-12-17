package com.prime.app.config;

public class GlobalSetting {

    public static final String SUPER_COMPANY_CODE = "SUPER";

    private GlobalSetting() {
    }

    public static final String ALPHA_NUMERIC_PATTERN = "^[a-zA-Z0-9]+$";

    public static final String X_FORWARDED_FOR = "X-FORWARDED-FOR";
    public static final String ID_GENERATION_STRATEGY = "uuid2";
    public static final String ID_GENERATION_NAME = "uuid";
    public static final String ID_GENERATOR = "uuid";

    public static final String COMMON_ERROR = "Something went wrong, please contact to support team";
    public static final String BAD_CREDENTIALS = "Invalid Authentication provided";

    public static final String DEFAULT_DATE_TIME_FORMAT = "EEE, d MMM yyyy hh:mm a";
    public static final String DATE_TIME_FORMAT = "dd/MM/yyy hh:mm a";
    public static final String DEFAULT_DATE_FORMAT = "EEE, d MMM yyyy";

    public static final String USER_LOGIN_EMAIL_INVALID = "user.login.email.invalid";
    public static final String MSG_EXCEPTION_SECURITY_TOKEN = "exception.security.token";
    public static final String UNKNOWN = "unknown";
    public static final String MSG_ACCOUNT_INACTIVE = "login.account.inactive";
    public static final String MSG_ACCOUNT_LOCK = "login.account.locked";
    public static final String MSG_BAD_CREDENTIALS = "login.failed";


}
