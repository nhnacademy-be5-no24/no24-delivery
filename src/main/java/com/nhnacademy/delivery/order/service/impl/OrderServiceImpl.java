package com.nhnacademy.delivery.order.service.impl;

import com.nhnacademy.delivery.order.dto.request.OrderCreateRequestDto;
import com.nhnacademy.delivery.order.dto.request.OrderRequestDto;
import com.nhnacademy.delivery.order.dto.response.OrderListForAdminResponseDto;
import com.nhnacademy.delivery.order.dto.response.OrderResponseDto;
import com.nhnacademy.delivery.order.exception.NotFoundOrderException;
import com.nhnacademy.delivery.order.repository.OrderRepository;
import com.nhnacademy.delivery.order.service.OrderService;
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


    // 주문리스트 전체 가져오기(admin)
    @Override
    @Transactional(readOnly = true)
    public Page<OrderListForAdminResponseDto> getOrders(Pageable pageable) {
       return orderRepository.getOrderList(pageable);
    }

    // 주문아이디로 상품리스트 가져오기
    @Override
    @Transactional(readOnly = true)
    public OrderResponseDto getOrderByOrderId(Long orderId) {
        Optional<OrderResponseDto> optionalOrder = orderRepository.getOrderByOrderId(orderId);
        if(optionalOrder.isEmpty()){
            throw new NotFoundOrderException(orderId);
        }
        return optionalOrder.get();
    }

    // 고객번호로 상품리스트 들고오기
    @Override
    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getOrdersByCustomer(
            Pageable pageable, Long customerNo) {
        return orderRepository.getOrdersListByCustomer(pageable, customerNo);
    }


    // 결제 완료되면 주문 저장하기
    @Override
    public OrderResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto) {
        return null;
    }


    // 주문 수정
    @Override
    public OrderResponseDto updateOrderState(OrderRequestDto orderRequestDto) {
        return null;
    }
}
