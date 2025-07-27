package com.danielnaiff.backend.Exception;

public class DocumentExists extends RuntimeException {
    public DocumentExists(String message) {
        super(message);
    }
}
