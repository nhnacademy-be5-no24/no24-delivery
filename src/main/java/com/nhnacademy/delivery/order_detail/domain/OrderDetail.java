package com.nhnacademy.delivery.order_detail.domain;

import com.nhnacademy.delivery.book.domain.Book;
import com.nhnacademy.delivery.order.domain.Order;
import com.nhnacademy.delivery.wrap.domain.Wrap;
import lombok.*;

import javax.persistence.*;

/**
 * 주문 상세(OrderDetail) 테이블.
 *
 * @author : 박동희
 * @date : 2024-04-04
 *
 **/
@Entity
@Getter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long orderDetailId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "book_isbn")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "wrap_id")
    private Wrap wrap;
}
