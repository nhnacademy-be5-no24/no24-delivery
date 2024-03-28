package com.nhnacademy.delivery.wrap.exception;

public class NotFoundWrapException extends RuntimeException{
    public NotFoundWrapException(Long wrapId){
        super("not found" + wrapId);
    }
}
