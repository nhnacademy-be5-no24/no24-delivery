package com.nhnacademy.rabbitmq.coupon_member.domain;

import com.nhnacademy.rabbitmq.coupon.domain.Coupon;
import com.nhnacademy.rabbitmq.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Coupon-Member Entity
 *
 * @Author : 박병휘
 * @Date : 2024/05/13
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponMember {
    public enum Status {
        ACTIVE, USED, DESTROYED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_member_id")
    private Long couponMemberId;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @ManyToOne
    @JoinColumn(name = "customer_no")
    private Member member;

    @Column(name = "used")
    private boolean used;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "destroyed_at")
    private LocalDateTime destroyedAt;

    @Column(name = "used_at")
    private LocalDateTime usedAt;

    @Column(name = "status")
    private Status status;
}
