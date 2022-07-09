package com.main.Difficult.Exceptions;

public class NotFoundException extends RuntimeException{
    private String message;
    public NotFoundException(){
        super();
    }
    public NotFoundException(String _message){
        super(_message);
    }
}
