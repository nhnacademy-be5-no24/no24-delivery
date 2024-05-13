package com.nhnacademy.rabbitmq.coupon_member.service;

/**
 * 설명
 *
 * @Author : 박병휘
 * @Date : 2024/05/13
 */
public interface CouponMemberService {
    void createCouponMember(Long couponId, Long customerNo);
}
