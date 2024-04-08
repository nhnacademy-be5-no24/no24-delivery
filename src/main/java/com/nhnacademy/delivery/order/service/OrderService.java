package com.nhnacademy.delivery.order.service;

import com.nhnacademy.delivery.customer.domain.Customer;
import com.nhnacademy.delivery.order.domain.Order;
import com.nhnacademy.delivery.order.dto.request.OrderCreateRequestDto;
import com.nhnacademy.delivery.order.dto.request.OrderRequestDto;
import com.nhnacademy.delivery.order.dto.response.OrderResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

/**
 * 주문(Order) service
 *
 * @author : 박동희
 * @date : 2024-04-04
 *
 **/
public interface OrderService {

    /**
     *  (admin) 주문 전체 조회를 위한 method.
     *
     * @return OrderResponDto 주문 정보 리스트로 반환됩니다.
     */
    Page<OrderResponseDto> getOrders(Pageable pageable);

    /**
     *  주문 id로 단건 조회를 위한 method.
     *
     * @param orderId 조회할 주문 아이디 입니다.
     * @return OrderResponseDto 주문 정보가 반환됩니다.
     */
    OrderResponseDto getOrderByOrderId(Long orderId);

    /**
     *  주문 날짜로 주문 조회를 위한 method.
     *
     * @param orderDate 조회할 주문 날짜 입니다.
     * @return OrderResponseDto 주문 정보가 반환됩니다.
     */
    Page<OrderResponseDto> getOrdersByOrderDate(LocalDate orderDate);

    /**
     * 주문 상태로 주문 조회를 위한 method.
     *
     * @param orderState 조회할 주문 상태입니다.
     * @return OrderResponseDto 주문 정보가 반환됩니다.
     */
    Page<OrderResponseDto> getOrdersByOrderState(Order.OrderState orderState);

    /**
     * 고객 아이디로 주문 조회를 위한 method.
     *
     * @param customer 조회할 customer 정보 입니다.
     * @return OrderResponseDto 주문 정보가 반환됩니다.
     */
    Page<OrderResponseDto> getOrdersByCustomer(Customer customer);


    /**
     *  주문 등록을 위한 method.
     * @param orderCreateRequestDto 주문 등록을 위한 정보 입니다.
     * @return OrderResponseDto 주문 정보가 반환됩니다.
     */
    OrderResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto);

    /**
     * 주문 상태(주문취소)를 위한 method.
     *
     * @param orderRequestDto 주문 상태(주문 취소)를 위한 정보 입니다.
     * @return OrderResponseDto 주문 정보가 반환됩니다.
     */
    OrderResponseDto deleteOrder(OrderRequestDto orderRequestDto);
}
