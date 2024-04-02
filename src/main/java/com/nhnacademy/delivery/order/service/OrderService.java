package com.nhnacademy.delivery.order.service;

import com.nhnacademy.delivery.order.dto.request.OrderCreateRequestDto;
import com.nhnacademy.delivery.order.dto.response.OrderResponseDto;

import java.util.List;

public interface OrderService {
    List<OrderResponseDto> getOrders();


    OrderResponseDto createOrder(OrderCreateRequestDto requestDto);

}
