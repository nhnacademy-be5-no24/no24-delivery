package com.nhnacademy.delivery.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class  OrderResponseDto {

    private Long orderId;
    private List<BookInfo> books;
    private LocalDate orderDate;
    private String receiverName;
    private String receiverPhoneNumber;
    private String address;
    private String addressDetail;
    private String orderState;


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
