package com.example.demo.exception;

public class SubcriberLimitException extends RuntimeException{

    public SubcriberLimitException(String message) {
        super(message);
    }
}
