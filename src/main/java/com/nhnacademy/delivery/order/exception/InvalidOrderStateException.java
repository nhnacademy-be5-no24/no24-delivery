package com.nhnacademy.delivery.order.exception;

import com.nhnacademy.delivery.order.domain.Order;

public class InvalidOrderStateException extends RuntimeException {
    public InvalidOrderStateException(Order.OrderState orderState) {
        super("없는 주문상태입니다. "+ orderState);
    }
}
