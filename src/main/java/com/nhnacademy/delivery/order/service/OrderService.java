package com.nhnacademy.delivery.order.service;

import com.nhnacademy.delivery.order.dto.request.OrderCreateRequestDto;
import com.nhnacademy.delivery.order.dto.request.OrderRequestDto;
import com.nhnacademy.delivery.order.dto.response.OrderResponseDto;

import java.util.List;

/**
 * 주문(Order) service
 *
 * @author : 박동희
 * @date : 2024-04-04
 *
 **/
public interface OrderService {


    /**
     *  주문 id로 단건 조회를 위한 method.
     *
     * @param orderId 조회할 주문 아이디 입니다.
     * @return OrderResponseDto 주문 정보가 반환됩니다.
     */
    OrderResponseDto getOrderByOrderId(Long orderId);

    OrderResponseDto createOrder(OrderCreateRequestDto requestDto);

}
