package com.nhnacademy.rabbitmq.member.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Member Entity
 *
 * @Author : 박병휘
 * @Date : 2024/05/13
 */
@Entity
public class Member {
    @Id
    @Column(name="customer_no")
    private Long customerNo;

    @Column(name="last_login_at")
    private LocalDateTime lastLoginAt;

    @Column(name="member_id")
    private String memberId;

    @Column(name="role")
    private String role;

    @Column(name="grade_id")
    private Long gradeId;
}
