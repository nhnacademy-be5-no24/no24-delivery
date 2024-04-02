package com.nhnacademy.delivery.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;

import java.time.LocalDate;

/**
 * 주문을 위한 request dto 입니다.
 *
 * @author : 박동희
 * @date : 2024-04-019
 **/
@Data
@AllArgsConstructor
@Builder
public class OrderRequestDto {
    private Long orderId;
    private LocalDate orderDate;
    private Long deliveryFee;
    private Long paymentId;
    private Integer orderState;
    private Long customerId;
    private String recipent;

}
