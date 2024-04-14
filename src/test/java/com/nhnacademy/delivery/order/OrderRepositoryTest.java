package com.nhnacademy.delivery.order;
import com.nhnacademy.delivery.book.domain.Book;
import com.nhnacademy.delivery.book.repository.BookRepository;
import com.nhnacademy.delivery.customer.domain.Customer;
import com.nhnacademy.delivery.customer.repository.CustomerRepository;
import com.nhnacademy.delivery.order.domain.Order;
import com.nhnacademy.delivery.order.dto.response.OrderListForAdminResponseDto;
import com.nhnacademy.delivery.order.dto.response.OrderResponseDto;
import com.nhnacademy.delivery.order.repository.OrderRepository;
import com.nhnacademy.delivery.order_detail.domain.OrderDetail;
import com.nhnacademy.delivery.order_detail.repository.OrderDetailRepository;
import com.nhnacademy.delivery.payment.domain.Payment;
import com.nhnacademy.delivery.payment.repository.PaymentRepository;
import com.nhnacademy.delivery.wrap.domain.Wrap;
import com.nhnacademy.delivery.wrap.repository.WrapRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles(value = "dev")
@Transactional
@WebAppConfiguration
class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private WrapRepository wrapRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    private Customer customer;
    private Book book;
    private Wrap wrap;
    private Order order;
    private OrderDetail orderDetail;
    private Payment payment;
    @BeforeEach
    void setup() {
        payment = Payment.builder()
                .paymentId(1L).paymentName("name").build();

        customer = Customer.builder()
                .customerNo(1L)
                .customerId("id")
                .customerPassword("password")
                .customerName("name")
                .customerPhoneNumber("number")
                .customerEmail("email")
                .customerBirthday(LocalDate.of(2024, 4, 14))
                .customerRole("role")
                .build();

        book = Book.builder()
                .bookIsbn("isbn")
                .bookTitle("title")
                .bookDesc("desc")
                .bookPublisher("publisher")
                .bookPublishAt(LocalDate.of(2024, 4, 14))
                .bookFixedPrice(1L)
                .bookSalePrice(1L)
                .bookIsPacking(true)
                .bookViews(1L)
                .bookStatus(0)
                .bookQuantity(1L)
                .bookImage("image")
                .build();

        wrap = Wrap.builder()
                .wrapId(1L)
                .wrapName("name")
                .wrapCost(1L)
                .build();

        order = Order.builder()
                .orderId(1L)
                .orderDate(LocalDate.of(2024, 4, 14))
                .deliveryFee(1L)
                .orderState(Order.OrderState.WAITING)
                .payment(payment)
                .customer(customer)
                .receiverName("name")
                .receiverPhoneNumber("number")
                .zipcode("zipcode")
                .address("address")
                .addressDetail("addressDetail")
                .build();

        orderDetail = OrderDetail.builder()
                .orderDetailId(1L)
                .book(book)
                .wrap(wrap)
                .order(order)
                .build();
    }

    @Test
    @DisplayName("모든 주문 리스트 반환")
    void testGetOrderList(){
        paymentRepository.save(payment);
        customerRepository.save(customer);
        bookRepository.save(book);
        wrapRepository.save(wrap);
        orderRepository.save(order);
        orderDetailRepository.save(orderDetail);

        Pageable pageable = PageRequest.of(0,10);
        Page<OrderListForAdminResponseDto> dtoPage = orderRepository.getOrderList(pageable);
        List<OrderListForAdminResponseDto> orderList = dtoPage.getContent();

        assertThat(orderList).isNotEmpty();
        Assertions.assertEquals(1,orderList.size());
        Assertions.assertEquals(1L,orderList.get(0).getBookSalePrices());
    }

    @Test
    @DisplayName("고객번호로 주문 리스트 반환")
    void testGetOrdersListByCustomer(){
        paymentRepository.save(payment);
        customerRepository.save(customer);
        bookRepository.save(book);
        wrapRepository.save(wrap);
        orderRepository.save(order);
        orderDetailRepository.save(orderDetail);

        Long customerNo = 1L;

        Pageable pageable = PageRequest.of(0,10);
        Page<OrderResponseDto> dtoPage = orderRepository.getOrdersListByCustomer(pageable,customerNo);
        List<OrderResponseDto> orderList = dtoPage.getContent();

        assertThat(orderList).isNotEmpty();
        Assertions.assertEquals(1,orderList.size());
        Assertions.assertEquals(1L,orderList.get(0).getOrderId());
    }
}
