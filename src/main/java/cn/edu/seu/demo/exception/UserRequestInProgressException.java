package cn.edu.seu.demo.exception;

public class UserRequestInProgressException extends Exception {
    public UserRequestInProgressException(String message) {
        super(message);
    }
}
