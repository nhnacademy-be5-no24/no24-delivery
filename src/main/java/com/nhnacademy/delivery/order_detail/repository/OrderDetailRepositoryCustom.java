package com.nhnacademy.delivery.order_detail.repository;

import com.nhnacademy.delivery.order_detail.domain.OrderDetail;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface OrderDetailRepositoryCustom {
    /**
     * 주문 번호로 주문상품들을 가져오는 메소드.
     *
     * @param orderId 주문번호.
     * @return 주문상품들.
     */
    List<OrderDetail> getOrderProductList(Long orderId);
}
