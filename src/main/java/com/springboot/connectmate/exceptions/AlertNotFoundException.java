package main.java.com.springboot.connectmate.exceptions;

public class AlertNotFoundException extends RuntimeException {

    public AlertNotFoundException(Long id) {
        super("Alert with id " + id + " not found");
    }
}