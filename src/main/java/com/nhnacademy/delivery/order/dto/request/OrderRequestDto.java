package com.nhnacademy.delivery.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * 주문을 위한 request dto 입니다.
 *
 * @author : 박동희
 * @date : 2024-04-05
 **/
@Data
@AllArgsConstructor
@Builder
public class OrderRequestDto {
    public enum OrderState{
        WAITING, DELIVERY, DONE, RETURNED, CANCELED, CONFIRMED

    }
    private Long orderId;
    private Integer orderDate;
    private Long deliveryFee;
    private Long paymentId;
    private Integer orderState;
    private Long customerId;
    private String recipent;

}
