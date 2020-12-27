package it.gualtierotesta.manning.liveproject.businessserver.exceptions;

public class NonExistentHealthProfileException extends RuntimeException {

    public NonExistentHealthProfileException(String message) {
        super(message);
    }
}
