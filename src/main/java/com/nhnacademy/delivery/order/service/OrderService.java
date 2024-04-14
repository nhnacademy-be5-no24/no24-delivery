package com.nhnacademy.delivery.order.service;

import com.nhnacademy.delivery.order.domain.Order;
import com.nhnacademy.delivery.order.dto.request.OrderCreateRequestDto;
import com.nhnacademy.delivery.order.dto.request.OrderModifyOrderStateRequestDto;
import com.nhnacademy.delivery.order.dto.response.OrderListForAdminResponseDto;
import com.nhnacademy.delivery.order.dto.response.OrderResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    Page<OrderListForAdminResponseDto> getOrders(Pageable pageable);

    /**
     *  주문 id로 단건 조회를 위한 method.
     *
     * @param orderId 조회할 주문 아이디 입니다.
     * @return OrderResponseDto 주문 정보가 반환됩니다.
     */
    OrderResponseDto getOrderByOrderId(Long orderId);



    /**
     * 고객 아이디로 주문 조회를 위한 method.
     *
     * @param pageable, customerNo 조회할 customerNo 입니다.
     * @return OrderResponseDto 주문 정보가 반환됩니다.
     */
    Page<OrderResponseDto> getOrdersByCustomer(Pageable pageable, Long customerNo);


    /**
     *  주문 등록을 위한 method.
     * @param orderCreateRequestDto 주문 등록을 위한 정보 입니다.
     * @return OrderResponseDto 주문 정보가 반환됩니다.
     */
    OrderResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto);


    /**
     *
     * @param orderId 조회할  주문 id.
     * @param orderState 수정할 state 정보
     * @return
     */
    void modifyOrderState(Long orderId, Order.OrderState orderState);
}
