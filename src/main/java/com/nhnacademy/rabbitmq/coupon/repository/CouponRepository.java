package com.nhnacademy.rabbitmq.coupon.repository;

import com.nhnacademy.rabbitmq.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Coupon JPA Repository
 *
 * @Author : 박병휘
 * @Date : 2024/05/13
 */
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
