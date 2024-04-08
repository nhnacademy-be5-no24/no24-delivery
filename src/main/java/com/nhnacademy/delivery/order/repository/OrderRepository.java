package com.nhnacademy.delivery.order.repository;

import com.nhnacademy.delivery.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom{


}
