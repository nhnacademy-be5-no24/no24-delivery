package com.nhnacademy.delivery.order.service.impl;

import com.nhnacademy.delivery.order.dto.request.OrderCreateRequestDto;
import com.nhnacademy.delivery.order.dto.request.OrderRequestDto;
import com.nhnacademy.delivery.order.dto.response.OrderListForAdminResponseDto;
import com.nhnacademy.delivery.order.dto.response.OrderListResponseDto;
import com.nhnacademy.delivery.order.dto.response.OrderResponseDto;
import com.nhnacademy.delivery.order.exception.NotFoundOrderException;
import com.nhnacademy.delivery.order.repository.OrderRepository;
import com.nhnacademy.delivery.order.service.OrderService;
import com.nhnacademy.delivery.order_detail.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 주문 서비스의 구현체입니다.
 *
 * @author : 박동희
 * @date : 2024-04-05
 **/
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;


    @Override
    @Transactional(readOnly = true)
    public Page<OrderListForAdminResponseDto> getOrders(Pageable pageable) {
       return orderRepository.getOrderList(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderListResponseDto getOrderByOrderId(Long orderId) {
        Optional<OrderListResponseDto> optionalOrder = orderRepository.getOrderByOrderId(orderId);
        if(optionalOrder.isEmpty()){
            throw new NotFoundOrderException(orderId);
        }
        return optionalOrder.get();
    }

    @Override
    public Page<OrderListResponseDto> getOrdersByCustomer(
            Pageable pageable, Long customerNo) {
        return orderRepository.getOrdersListByCustomer(pageable, customerNo);
    }


    @Override
    public OrderListResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto) {
        return null;
    }

    @Override
    public OrderResponseDto updateOrderState(OrderRequestDto orderRequestDto) {
        return null;
    }
}
