package com.nhnacademy.delivery.order.dto.response;

import com.nhnacademy.delivery.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class OrderListForAdminResponseDto {
    private Long orderId;
    private LocalDate orderDate;
    private Order.OrderState orderState;
    private Long deliverFee;
    private Long paymentId;
    private Long customerNo;
    private Long zipcode;

}
