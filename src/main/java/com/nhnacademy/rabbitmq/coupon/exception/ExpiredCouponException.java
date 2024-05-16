package com.nhnacademy.rabbitmq.coupon.exception;

/**
 * 쿠폰을 찾지 못 했을 때 발생하는 Exception 입니다.
 *
 * @author : 박병휘
 * @date : 2024/03/29
 */
public class ExpiredCouponException extends RuntimeException {
    public ExpiredCouponException(Long couponId) {
        super(couponId + " is expired.");
    }
}
