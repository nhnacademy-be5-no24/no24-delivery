package com.nhnacademy.rabbitmq.coupon_member.service.impl;

import com.nhnacademy.rabbitmq.coupon.domain.Coupon;
import com.nhnacademy.rabbitmq.coupon.exception.ExpiredCouponException;
import com.nhnacademy.rabbitmq.coupon.exception.NotFoundCouponException;
import com.nhnacademy.rabbitmq.coupon.repository.CouponRepository;
import com.nhnacademy.rabbitmq.coupon_member.domain.CouponMember;
import com.nhnacademy.rabbitmq.coupon_member.repository.CouponMemberRepository;
import com.nhnacademy.rabbitmq.coupon_member.service.CouponMemberService;
import com.nhnacademy.rabbitmq.member.domain.Member;
import com.nhnacademy.rabbitmq.member.exception.MemberNotFoundException;
import com.nhnacademy.rabbitmq.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 설명
 *
 * @Author : 박병휘
 * @Date : 2024/05/13
 */
@Service
@AllArgsConstructor
public class CouponMemberServiceImpl implements CouponMemberService {
    private final CouponMemberRepository couponMemberRepository;
    private final CouponRepository couponRepository;
    private final MemberRepository memberRepository;


    @Override
    @Transactional
    public void createCouponMember(Long couponId, Long customerNo) {
        Optional<Coupon> optionalCoupon = couponRepository.findById(couponId);
        Optional<Member> optionalMember = memberRepository.findById(customerNo);

        if(optionalCoupon.isEmpty()) {
            throw new NotFoundCouponException(couponId);
        }

        if(optionalMember.isEmpty()) {
            throw new MemberNotFoundException();
        }

        if(optionalCoupon.get().getDeadline().atStartOfDay().isAfter(LocalDateTime.now())) {
            CouponMember couponMember = CouponMember.builder()
                    .couponMemberId(null)
                    .coupon(optionalCoupon.get())
                    .member(optionalMember.get())
                    .used(false)
                    .createdAt(LocalDateTime.now())
                    .destroyedAt(optionalCoupon.get().getDeadline().atStartOfDay())
                    .usedAt(null)
                    .status(CouponMember.Status.ACTIVE)
                    .build();

            couponMember = couponMemberRepository.save(couponMember);
        }
        else {
            throw new ExpiredCouponException(couponId);
        }
    }
}
