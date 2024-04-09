package com.nhnacademy.delivery.order.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class OrderListResponseDto {

    private Long orderId;
    private List<BookInfo> books;
    private LocalDate orderDate;
    private String receiverName;
    private String receiverPhoneNumber;
    private String address;
    private String orderState;

    public OrderListResponseDto(Long orderId, List<BookInfo> books, LocalDate orderDate, String receiverName, String receiverPhoneNumber, String address, String orderState) {
        this.orderId = orderId;
        this.books = books;
        this.orderDate = orderDate;
        this.receiverName = receiverName;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.address = address;
        this.orderState = orderState;
    }

    @Getter
    @Builder
    public static class BookInfo {
        private String title;
        private double salePrice;
        private double wrapCost;

        public BookInfo(String title, double salePrice, double wrapCost) {
            this.title = title;
            this.salePrice = salePrice;
            this.wrapCost = wrapCost;
        }

    }
}
