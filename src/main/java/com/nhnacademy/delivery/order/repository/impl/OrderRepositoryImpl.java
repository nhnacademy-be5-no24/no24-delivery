package com.nhnacademy.delivery.order.repository.impl;

import com.nhnacademy.delivery.order.domain.Order;
import com.nhnacademy.delivery.order.dto.response.OrderListForAdminResponseDto;
import com.nhnacademy.delivery.order.dto.response.OrderListResponseDto;
import com.nhnacademy.delivery.order.repository.OrderRepositoryCustom;
import com.nhnacademy.delivery.order_detail.domain.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class OrderRepositoryImpl extends QuerydslRepositorySupport implements OrderRepositoryCustom {

    public OrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public Page<OrderListForAdminResponseDto> getOrderList(Pageable pageable) {
        return null;
    }

    @Override
    public Page<OrderListResponseDto> getOrdersListByCustomer(Pageable pageable, Long customerNo) {
        return null;
    }

    @Override
    public List<OrderDetail> getOrderProductList(Long orderId) {
        return null;
    }
}
