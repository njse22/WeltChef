package appmoviles.com.weltchef.exceptions;

import androidx.annotation.Nullable;

public class UserNotRegisterException extends Exception {

    private String message;

    public UserNotRegisterException(String message) {
        super(message);
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }
}
