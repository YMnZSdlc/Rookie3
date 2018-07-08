package bookstore.users.exception;

public class UserNotExistsExeception extends RuntimeException {
    public UserNotExistsExeception(String message){
        super(message);
    }
}
