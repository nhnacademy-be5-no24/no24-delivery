package com.nhnacademy.delivery.order.repository.impl;

import com.nhnacademy.delivery.book.domain.QBook;
import com.nhnacademy.delivery.customer.domain.QCustomer;
import com.nhnacademy.delivery.order.domain.Order;
import com.nhnacademy.delivery.order.domain.QOrder;
import com.nhnacademy.delivery.order.dto.response.OrderListForAdminResponseDto;
import com.nhnacademy.delivery.order.dto.response.OrderListResponseDto;
import com.nhnacademy.delivery.order.repository.OrderRepositoryCustom;
import com.nhnacademy.delivery.order_detail.domain.OrderDetail;
import com.nhnacademy.delivery.order_detail.domain.QOrderDetail;
import com.nhnacademy.delivery.wrap.domain.QWrap;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

public class OrderRepositoryImpl extends QuerydslRepositorySupport implements OrderRepositoryCustom {

    public OrderRepositoryImpl() {
        super(Order.class);
    }

    QOrder order = QOrder.order;
    QOrderDetail orderDetail = QOrderDetail.orderDetail;
    QCustomer customer = QCustomer.customer;
    QBook book = QBook.book;
    QWrap wrap = QWrap.wrap;
    @Override
    public Page<OrderListForAdminResponseDto> getOrderList(Pageable pageable) {
        JPQLQuery<Long> cnt = from(order)
                .select(order.orderId.count());


        JPQLQuery<OrderListForAdminResponseDto> query = from(order)
                .select(Projections.constructor(
                        OrderListForAdminResponseDto.class,
                        order.orderId.as("주문번호"),
                        order.customer.customerNo.as("고객번호"),
                        order.orderDate.as("주문날짜/배송날짜"),
                        order.orderState.as("주문상태"),
                        wrap.wrapCost.as("포장지 가격"),
                        book.bookTitle.as("도서 이름"),
                        orderDetail.book.bookSalePrice.as("도서가격")
                ))
                .innerJoin(order.customer, customer)
                .innerJoin(order.orderDetails, orderDetail)
                .innerJoin(orderDetail.wrap, wrap)
                .innerJoin(orderDetail.book, book)
                .orderBy(order.orderDate.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset());

        return PageableExecutionUtils.getPage(query.fetch(), pageable, cnt::fetchOne);
    }

    @Override
    public Page<OrderListResponseDto> getOrdersListByCustomer(Pageable pageable, Long customerNo) {
        JPQLQuery<OrderListResponseDto> query = from(order)
                .select(Projections.constructor(
                        OrderListResponseDto.class,
                        order.orderId.as("주문번호"),
                        orderDetail.book.bookTitle.as("도서"),
                        orderDetail.book.bookSalePrice.as("도서 판매 가격"),
                        orderDetail.wrap.wrapCost.as("포장가격"),
                        order.orderDate.as("주문날짜"),
                        order.receiverName.as("수취인"),
                        order.receiverPhoneNumber.as("수취인 전화번호"),
                        order.address.as("주소"),
                        order.orderState.as("배송상태")
                ))
                .innerJoin(order.customer, customer)
                .innerJoin(order.orderDetails, orderDetail)
                .where(customer.customerNo.eq(customerNo))
                .orderBy(order.orderDate.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset());

        return PageableExecutionUtils.getPage(query.fetch(), pageable, query::fetchCount);
    }


}
