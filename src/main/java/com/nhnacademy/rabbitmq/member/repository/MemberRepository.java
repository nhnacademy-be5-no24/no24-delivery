package com.nhnacademy.rabbitmq.member.repository;

import com.nhnacademy.rabbitmq.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CouponMember JPA Repository
 *
 * @Author : 박병휘
 * @Date : 2024/05/13
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
}
