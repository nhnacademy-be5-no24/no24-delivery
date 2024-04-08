package com.nhnacademy.delivery.order.dto.request;

import com.nhnacademy.delivery.customer.domain.Customer;
import com.nhnacademy.delivery.payment.domain.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@Builder
public class OrderCreateRequestDto {
    private LocalDate orderDate;
    private Long deliveryFee;
    private Payment payment;
    private Customer customer;
    private String receiverName;
    private String receiverPhoneNumber;
    private String zipcode;
    private String address;
    private String addressDetail;
    private String req;
}
