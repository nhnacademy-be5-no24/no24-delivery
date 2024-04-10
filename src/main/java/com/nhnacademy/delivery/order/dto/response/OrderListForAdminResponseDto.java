package com.nhnacademy.delivery.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class OrderListForAdminResponseDto {
    private Long orderId;
    private Long customerNo;
    private String customerName;
    private String customerPhoneNumber;
    private String customerRole;
    private LocalDate orderDate;
    private String orderState;
    private String wrapName;
    private Long wrapCosts;
    private String bookTitles;
    private Long bookSalePrices;

}