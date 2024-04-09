package com.nhnacademy.delivery.order_detail.repository.impl;

import com.nhnacademy.delivery.book.domain.QBook;
import com.nhnacademy.delivery.order.domain.QOrder;
import com.nhnacademy.delivery.order_detail.domain.OrderDetail;
import com.nhnacademy.delivery.order_detail.domain.QOrderDetail;
import com.nhnacademy.delivery.order_detail.repository.OrderDetailRepositoryCustom;
import com.nhnacademy.delivery.wrap.domain.QWrap;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class OrderDetailRepositoryImpl extends QuerydslRepositorySupport implements OrderDetailRepositoryCustom {

    public OrderDetailRepositoryImpl(){
        super(OrderDetail.class);
    }

    QOrder order = QOrder.order;
    QOrderDetail orderDetail = QOrderDetail.orderDetail;
    QBook book = QBook.book;
    QWrap wrap = QWrap.wrap;

    @Override
    public List<OrderDetail> getOrderProductList(Long orderId) {
        return from(orderDetail)
                .select(orderDetail)
                .innerJoin(order).on(orderDetail.order.orderId.eq(order.orderId))
                .innerJoin(orderDetail.book, book)
                .innerJoin(orderDetail.wrap, wrap)
                .where(orderDetail.order.orderId.eq(orderId)).fetch();
    }
}
