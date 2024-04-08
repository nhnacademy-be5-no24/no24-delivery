package com.nhnacademy.delivery.order.exception;

public class NotFoundOrderException extends RuntimeException{
    public NotFoundOrderException(Long orderId){
        super("not found "+orderId);
    }
}
