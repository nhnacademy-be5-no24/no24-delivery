package com.nhnacademy.delivery.order.dto.response;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class  OrderListForAdminResponseDto {
    private String orderId;
    private String customerName;
    private String wrapName;
    private Long wrapCosts;
    private String bookTitles;
    private Long bookSalePrices;


    public OrderListForAdminResponseDto(String orderId, String customerName, String wrapName, Long wrapCosts, String bookTitles, Long bookSalePrices) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.wrapName = wrapName;
        this.wrapCosts = wrapCosts;
        this.bookTitles = bookTitles;
        this.bookSalePrices = bookSalePrices;
    }
}