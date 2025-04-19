package com.example.LaboSpringbootRESTAPI.exception;

public class MissingApiKeyException extends RuntimeException {
    public MissingApiKeyException(String message) {
        super(message);
    }
}