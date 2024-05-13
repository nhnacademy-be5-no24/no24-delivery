package com.nhnacademy.rabbitmq.coupon_member.repository;

import com.nhnacademy.rabbitmq.coupon_member.domain.CouponMember;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CouponMember JPA Repository
 *
 * @Author : 박병휘
 * @Date : 2024/05/13
 */
public interface CouponMemberRepository extends JpaRepository<CouponMember, Long> {
}
