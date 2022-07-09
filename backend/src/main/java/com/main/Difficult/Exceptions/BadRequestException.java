package com.main.Difficult.Exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(){
        super();
    }
    public BadRequestException(String _message){
        super(_message);
    }
}
