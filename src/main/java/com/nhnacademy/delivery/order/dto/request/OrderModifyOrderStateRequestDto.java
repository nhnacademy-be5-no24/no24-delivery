package com.nhnacademy.delivery.order.dto.request;

import com.nhnacademy.delivery.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 주문을 위한 request dto 입니다.
 *
 * @author : 박동희
 * @date : 2024-04-05
 **/
@Data
@AllArgsConstructor
@Builder
public class OrderModifyOrderStateRequestDto {
    private Order.OrderState orderState;
}
