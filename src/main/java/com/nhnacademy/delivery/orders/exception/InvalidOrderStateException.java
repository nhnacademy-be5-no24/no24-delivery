package com.nhnacademy.delivery.orders.exception;


import com.nhnacademy.delivery.orders.domain.Orders;

public class InvalidOrderStateException extends RuntimeException {
    public InvalidOrderStateException(Orders.OrderState orderState) {
        super("없는 주문상태입니다. "+ orderState);
    }
}
