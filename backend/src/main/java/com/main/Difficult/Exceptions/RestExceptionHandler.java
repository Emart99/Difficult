package com.main.Difficult.Exceptions;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ErrorInfo> handleNotFoundException(Exception _exception){
        return new ResponseEntity(new ErrorInfo(HttpStatus.NOT_FOUND, _exception),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BusinessException.class)
    ResponseEntity<ErrorInfo> handleBusinessException(Exception _exception){
        return new ResponseEntity(new ErrorInfo(HttpStatus.UNPROCESSABLE_ENTITY, _exception),HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(CarritoException.class)
    ResponseEntity<ErrorInfo> handleCarritoException(Exception _exception){
        return new ResponseEntity(new ErrorInfo(HttpStatus.UNAUTHORIZED, _exception),HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ErrorInfo> handleRuntimeException(Exception _exception){
        return new ResponseEntity(new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR, _exception),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ResponseEntity<ErrorInfo> handleMethodArgumentTypeMismatch(Exception _exception){
        return new ResponseEntity(new ErrorInfo(HttpStatus.BAD_REQUEST, _exception),HttpStatus.BAD_REQUEST);
    }
    @Data
    public static class ErrorInfo{
        public Boolean status;
        public String message;
        public Integer statusCode;

        @JsonPropertyOrder(value = {"status", "mensaje", "statusCode"})
        public ErrorInfo(HttpStatus _statusCode,Exception _exception ){
            status = false;
            message = _exception.getLocalizedMessage();
            statusCode = _statusCode.value();
        }
        public ErrorInfo(HttpStatus _statusCode,String _exception ){
            status = false;
            message = _exception;
            statusCode = _statusCode.value();
        }
    }
}
