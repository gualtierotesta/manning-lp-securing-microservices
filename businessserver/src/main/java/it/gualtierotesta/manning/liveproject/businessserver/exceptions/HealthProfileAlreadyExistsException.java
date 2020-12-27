package it.gualtierotesta.manning.liveproject.businessserver.exceptions;

public class HealthProfileAlreadyExistsException extends RuntimeException {

    public HealthProfileAlreadyExistsException(String message) {
        super(message);
    }
}
