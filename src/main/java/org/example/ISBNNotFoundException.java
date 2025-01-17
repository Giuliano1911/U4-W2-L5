package org.example;

public class ISBNNotFoundException extends RuntimeException {
    public ISBNNotFoundException(String message) {
        super(message);
    }
}
