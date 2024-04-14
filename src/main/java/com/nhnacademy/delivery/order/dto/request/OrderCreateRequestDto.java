package com.nhnacademy.delivery.order.dto.request;

import com.nhnacademy.delivery.customer.domain.Customer;
import com.nhnacademy.delivery.order.domain.Order;
import com.nhnacademy.delivery.order_detail.domain.OrderDetail;
import com.nhnacademy.delivery.payment.domain.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class OrderCreateRequestDto {
    private LocalDate orderDate;
    private Order.OrderState orderState;
    private Long deliveryFee;
    private Payment payment;
    private Customer customer;
    private String receiverName;
    private String receiverPhoneNumber;
    private String zipcode;
    private String address;
    private String addressDetail;
    private String req;
    private List<OrderDetail> orderDetailList;
}
