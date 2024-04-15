package com.nhnacademy.delivery.order.exception;

public class NotFoundOrderException extends RuntimeException{
    public NotFoundOrderException(String orderId){
        super("not found "+orderId);
    }
}
