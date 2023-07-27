package com.example.shoppingverse.Exception;

public class InsufficientQuantityException extends RuntimeException{

    public InsufficientQuantityException(String message) {
        super(message);
    }

}
