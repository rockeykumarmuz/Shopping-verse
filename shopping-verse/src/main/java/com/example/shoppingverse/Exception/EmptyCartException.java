package com.example.shoppingverse.Exception;

public class EmptyCartException extends RuntimeException{

    public EmptyCartException(String msg) {
        super(msg);
    }
}
