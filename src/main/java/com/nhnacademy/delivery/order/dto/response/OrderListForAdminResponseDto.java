package com.nhnacademy.delivery.order.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class OrderListForAdminResponseDto {
    private Long orderId;
    private Long customerNo;
    private LocalDate orderDate;
    private String orderState;
    private List<Long> wrapCosts;
    private List<String> bookTitles;
    private List<Long> bookSalePrices;

    public OrderListForAdminResponseDto(Long orderId,
                                        Long customerNo,
                                        LocalDate orderDate,
                                        String orderState,
                                        List<Long> wrapCosts,
                                        List<String> bookTitles,
                                        List<Long> bookSalePrices) {
        this.orderId = orderId;
        this.customerNo = customerNo;
        this.orderDate = orderDate;
        this.orderState = orderState;
        this.wrapCosts = wrapCosts;
        this.bookTitles = bookTitles;
        this.bookSalePrices = bookSalePrices;
    }

}