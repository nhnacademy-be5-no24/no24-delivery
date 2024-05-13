package com.nhnacademy.rabbitmq.coupon.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * Coupon Entity
 *
 * @Author : 박병휘
 * @Date : 2024/05/13
 */

@Entity
@Getter
public class Coupon {
    public enum CouponTarget {
        NORMAL, CATEGORY, BOOK
    }
    public enum CouponType {
        PERCENTAGE, AMOUNT
    }

    public enum Status {
        ACTIVE, DEACTIVATED
    }

    @Id
    @Column(name="coupon_id")
    private Long couponId;

    @Column(name="coupon_name")
    private String couponName;

    @Column(name="coupon_status")
    private Status couponStatus;

    @Column(name="coupon_target")
    private CouponTarget couponTarget;

    @Column(name="coupon_type")
    private CouponType couponType;

    @Column(name="deadline")
    private LocalDate deadline;

    @Column(name="expiration_period")
    private int expirationPeriod;

    @Column(name="issue_limit")
    private int issueLimit;
}
