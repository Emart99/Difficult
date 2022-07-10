package com.main.Difficult.Exceptions;

import java.util.function.Supplier;

public class NotFoundException extends RuntimeException {
    private String message;
    public NotFoundException(){
        super();
    }
    public NotFoundException(String _message){
        super(_message);
    }
}
