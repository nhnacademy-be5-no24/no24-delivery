package com.nhnacademy.delivery.wrap.exception;

public class AlreadyExistWrapException extends RuntimeException{
    public AlreadyExistWrapException(String wrapName){
        super("already wrap "+ wrapName);
    }
}
