package com.nhnacademy.delivery.order.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class  OrderResponseDto {
    private Long orderId;
    private List<OrderInfo> orders;
    private List<BookInfo> books;

    public OrderResponseDto(Long orderId,List<OrderInfo> orders, List<BookInfo> books) {
        this.orderId = orderId;
        this.orders = orders;
        this.books = books;
    }

    @Getter
    @Builder
    public static class OrderInfo{
        private LocalDate orderDate;
        private String receiverName;
        private String receiverPhoneNumber;
        private String address;
        private String addressDetail;
        private String orderState;
        public OrderInfo(LocalDate orderDate, String receiverName, String receiverPhoneNumber, String address, String addressDetail, String orderState) {
            this.orderDate = orderDate;
            this.receiverName = receiverName;
            this.receiverPhoneNumber = receiverPhoneNumber;
            this.address = address;
            this.addressDetail = addressDetail;
            this.orderState = orderState;
        }

    }
    @Getter
    @Builder
    public static class BookInfo {
        private String title;
        private Long salePrice;
        private String wrapName;
        private Long wrapCost;

        public BookInfo(String title, Long salePrice, String wrapName, Long wrapCost) {
            this.title = title;
            this.salePrice = salePrice;
            this.wrapName = wrapName;
            this.wrapCost = wrapCost;
        }

    }
}
