package com.nhnacademy.delivery.order.exception;

public class OrderStatusFailedException extends RuntimeException{
    public OrderStatusFailedException(String orderState){
        super("invaild orderstate "+ orderState);
    }
}
