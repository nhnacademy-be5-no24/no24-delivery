package com.nhnacademy.delivery.order.service.impl;

import com.nhnacademy.delivery.customer.domain.Customer;
import com.nhnacademy.delivery.customer.repository.CustomerRepository;
import com.nhnacademy.delivery.order.domain.Order;
import com.nhnacademy.delivery.order.dto.request.OrderCreateRequestDto;
import com.nhnacademy.delivery.order.dto.request.OrderRequestDto;
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

import java.time.LocalDate;
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
    private final CustomerRepository customerRepository;

    /**
     * 주문 전체 조회를 위한 method.
     *
     * @return OrderResponseDto 페이지를 반환.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getOrders(Pageable pageable) {
        Page<Order> orderpage = orderRepository.findAll(pageable);
        return orderpage.map(order -> new OrderResponseDto(
                order.getOrderId(),
                order.getOrderDate(),
                order.getOrderState(),
                order.getDeliveryFee(),
                order.getPayment().getPaymentId(),
                order.getAddress(),
                order.getReceiverName(),
                order.getCustomer().getCustomerNo(),
                order.getReceiverName())
        );
    }

    /**
     * 주문아이디로 주문 단건 조회를 위한 method.
     *
     * @param orderId 주문 아이디.
     * @return OrderResponseDto 페이지 반환.
     */
    @Override
    @Transactional(readOnly = true)
    public OrderResponseDto getOrderByOrderId(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new NotFoundOrderException(orderId);
        }
        return returnResponseDto(optionalOrder.get());
    }

    @Override
    public Page<OrderResponseDto> getOrdersByOrderDate(LocalDate orderDate) {
        return null;
    }

    @Override
    public Page<OrderResponseDto> getOrdersByOrderState(Order.OrderState orderState) {
        return null;
    }

    @Override
    public Page<OrderResponseDto> getOrdersByCustomer(Customer customer) {
        return null;
    }

    @Override
    public OrderResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto) {
        return null;
    }

    @Override
    public OrderResponseDto deleteOrder(OrderRequestDto orderRequestDto) {
        return null;
    }

    private OrderResponseDto returnResponseDto(Order order){
        return new OrderResponseDto(order.getOrderId(),
                order.getOrderDate(),
                order.getOrderState(),
                order.getDeliveryFee(),
                order.getPayment().getPaymentId(),
                order.getAddress(),
                order.getReceiverName(),
                order.getCustomer().getCustomerNo(),
                order.getReq()
        );
    }
}
