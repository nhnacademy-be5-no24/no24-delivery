package com.nhnacademy.rabbitmq.receiver;

import com.nhnacademy.rabbitmq.coupon_member.service.CouponMemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 설명
 *
 * @Author : 박병휘
 * @Date : 2024/05/13
 */
@Component
@AllArgsConstructor
@Slf4j
public class MessageReceiver {
    private final CouponMemberService couponMemberService;
    private final AmqpTemplate rabbitTemplate;

    @RabbitListener(queues="COUPON_QUEUE")
    public void receiveMessage(String message) {
        // coupon_id: ${coupon_id}, member_id: ${member_id}
        String couponMessage = message.split(", ")[0];
        String memberMessage = message.split(", ")[1];

        Long couponId = Long.parseLong(couponMessage.replaceAll(" ", "").split(":")[1]);
        Long memberId = Long.parseLong(memberMessage.replaceAll(" ", "").split(":")[1]);

        couponMemberService.createCouponMember(couponId, memberId);
        log.info("Success issuing -> " + message);
    }
}
