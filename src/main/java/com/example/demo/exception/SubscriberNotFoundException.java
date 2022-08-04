package com.example.demo.exception;

public class SubscriberNotFoundException extends RuntimeException{

    public SubscriberNotFoundException(String message) {
        super(message);
    }
}
