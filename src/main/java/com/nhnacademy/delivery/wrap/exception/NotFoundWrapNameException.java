package com.nhnacademy.delivery.wrap.exception;

public class NotFoundWrapNameException extends RuntimeException{
    public NotFoundWrapNameException(String wrapName){
        super("not found "+ wrapName);
    }
}
