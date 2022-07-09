package com.main.Difficult.Exceptions;

public class BusinessException extends RuntimeException{
    public BusinessException(){
        super();
    }
    public BusinessException(String _message){
        super(_message);
    }
}
