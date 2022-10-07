package com.youtap.constants;

public class AppConstants {

    public static final String RETRIEVE_USERS_URI = "/getusercontacts";

    public static final String USER_NOT_FOUND = "User not found";
    public static final String SUCCESS = "Success";
    public static final String FAILED = "Failed";
    public static final String INVALID_REQUEST = "Both UserID and Username cannot be null";
    public static final String GENERIC_FAILURE = "Error while executing request";

    public class SwaggerConstants {
        public static final String PUBLIC_RELEASED = "Public - Released";

        private SwaggerConstants() {
        }
    }
}
