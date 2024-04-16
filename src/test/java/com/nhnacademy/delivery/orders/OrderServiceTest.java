package com.nhnacademy.delivery.orders;

import com.nhnacademy.delivery.book.domain.Book;
import com.nhnacademy.delivery.customer.domain.Customer;
import com.nhnacademy.delivery.order_detail.domain.OrderDetail;
import com.nhnacademy.delivery.orders.domain.Orders;
import com.nhnacademy.delivery.orders.dto.request.OrdersCreateRequestDto;
import com.nhnacademy.delivery.orders.dto.response.OrdersListForAdminResponseDto;
import com.nhnacademy.delivery.orders.dto.response.OrdersResponseDto;
import com.nhnacademy.delivery.orders.exception.NotFoundOrderException;
import com.nhnacademy.delivery.orders.exception.OrderStatusFailedException;
import com.nhnacademy.delivery.orders.exception.SaveOrderFailed;
import com.nhnacademy.delivery.orders.repository.OrdersRepository;
import com.nhnacademy.delivery.orders.service.OrdersService;
import com.nhnacademy.delivery.orders.service.impl.OrdersServiceImpl;
import com.nhnacademy.delivery.payment.domain.Payment;
import com.nhnacademy.delivery.wrap.domain.Wrap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Transactional
@WebAppConfiguration
class OrderServiceTest {
    @Mock
    private final OrdersRepository ordersRepository = mock(OrdersRepository.class);
    private OrdersService ordersService;

    private Customer customer;
    private Book book;
    private Wrap wrap;
    private Orders order;
    private OrderDetail orderDetail;
    private Payment payment;
    private OrdersListForAdminResponseDto adminResponseDto;
    private OrdersListForAdminResponseDto adminResponseDto2;
    private OrdersResponseDto ordersResponseDto;
    private OrdersResponseDto ordersResponseDto2;
    private OrdersCreateRequestDto createRequestDto;
    private OrdersCreateRequestDto createRequestDtoNoState;

    @BeforeEach
    void setup() {
        ordersService = new OrdersServiceImpl(ordersRepository);
        initializeEntities();
        initializeAdminResponseDto();
    }

    private void initializeEntities() {
        payment = Payment.builder()
                .paymentId(1L).paymentName("paymentName").build();

        customer = Customer.builder()
                .customerNo(1L)
                .customerId("customerId")
                .customerPassword("customerPassword")
                .customerName("customerName")
                .customerPhoneNumber("customerPhoneNumber")
                .customerEmail("customerEmail")
                .customerBirthday(LocalDate.of(2024, 4, 15))
                .customerRole("user")
                .build();

        book = Book.builder()
                .bookIsbn("Isbn")
                .bookTitle("Title")
                .bookDesc("desc")
                .bookPublisher("publisher")
                .bookPublishAt(LocalDate.of(2024, 4, 15))
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
                .wrapName("wrapName")
                .wrapCost(1L)
                .build();

        order = Orders.builder()
                .orderId("orderId")
                .orderDate(LocalDate.of(2024, 4, 15))
                .deliveryFee(1L)
                .orderState(Orders.OrderState.WAITING)
                .payment(payment)
                .customer(customer)
                .receiverName("receiverName")
                .receiverPhoneNumber("receiverPhoneNumber")
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
    private void initializeAdminResponseDto() {
        createRequestDto = new OrdersCreateRequestDto(
                LocalDate.of(2024, 4, 15),
                Orders.OrderState.COMPLETE_PAYMENT,
                1L,
                payment,
                customer,
                "receiverName",
                "receiverPhoneNumber",
                "zipcode",
                "address",
                "addressDetail",
                "req",
                Collections.singletonList(orderDetail)
        );
        createRequestDtoNoState = new OrdersCreateRequestDto(
                LocalDate.of(2024, 4, 15),
                Orders.OrderState.WAITING,
                1L,
                payment,
                customer,
                "receiverName",
                "receiverPhoneNumber",
                "zipcode",
                "address",
                "addressDetail",
                "req",
                Collections.singletonList(orderDetail)
        );

        adminResponseDto = OrdersListForAdminResponseDto.builder()
                .orderId("orderId")
                .customerName("customerName")
                .orderDate(LocalDate.of(2024,4,15))
                .orderState(Orders.OrderState.WAITING)
                .address("address")
                .addressDetail("addressDetail")
                .wrapName("wrapName")
                .wrapCost(1L)
                .bookTitle("bookTittle")
                .bookSalePrice(1L).build();
        adminResponseDto2 = OrdersListForAdminResponseDto.builder()
                .orderId("orderId2")
                .customerName("customerName2")
                .orderDate(LocalDate.of(2024,4,15))
                .orderState(Orders.OrderState.WAITING)
                .address("address")
                .addressDetail("addressDetail")
                .wrapName("wrapName")
                .wrapCost(1L)
                .bookTitle("bookTittle")
                .bookSalePrice(1L).build();

         ordersResponseDto = new OrdersResponseDto("orderId",
                "bookTitle",
                1L,
                "wrapName",
                1L,
                LocalDate.of(2024, 4, 15),
                "receiverName",
                "phoneNumber",
                "address",
                "addressDetail",
                Orders.OrderState.WAITING);
        ordersResponseDto2 = new OrdersResponseDto("orderId2",
                "bookTitle",
                1L,
                "wrapName",
                1L,
                LocalDate.of(2024, 4, 15),
                "receiverName",
                "phoneNumber",
                "address",
                "addressDetail",
                Orders.OrderState.COMPLETED);


    }
    @Test
    @DisplayName("주문리스트 전체 가져오기")
    void testGetOrders() {
        List<OrdersListForAdminResponseDto> ordersList = Arrays.asList(adminResponseDto, adminResponseDto2);
        Page<OrdersListForAdminResponseDto> mockedPage = new PageImpl<>(ordersList);
        when(ordersRepository.getOrderList(any(Pageable.class))).thenReturn(mockedPage);

        Page<OrdersListForAdminResponseDto> result = ordersService.getOrders(mock(Pageable.class));

        verify(ordersRepository, times(1)).getOrderList(any(Pageable.class));
        assertEquals(mockedPage, result);
        assertEquals(2, result.get().count());
    }

    @Test
    @DisplayName("주문아이디로 상품리스트 가져오기")
    void testGetOrderByOrdersId() {
        when(ordersRepository.getOrderByOrderId(anyString())).thenReturn(Optional.of(ordersResponseDto));

        OrdersResponseDto result = ordersService.getOrderByOrdersId("orderId");

        verify(ordersRepository, times(1)).getOrderByOrderId("orderId");
        assertEquals(ordersResponseDto, result);
        assertEquals("orderId", ordersResponseDto.getOrderId());
    }

    @Test
    @DisplayName("고객번호로 상품리스트 가져오기")
    void testGetOrderByCustomer() {

        List<OrdersResponseDto> mockedResponse = Arrays.asList(ordersResponseDto, ordersResponseDto2);
        Page<OrdersResponseDto> mockedPage = new PageImpl<>(mockedResponse);
        when(ordersRepository.getOrderListByCustomer(any(Pageable.class), eq(1L))).thenReturn(mockedPage);

        Page<OrdersResponseDto> result = ordersService.getOrderByCustomer(PageRequest.of(0, 10), 1L);

        verify(ordersRepository, times(1)).getOrderListByCustomer(any(Pageable.class), eq(1L));
        assertEquals(mockedPage, result);
    }

    @Test
    @DisplayName("주문 생성 - 성공")
    void testCreateOrder_Success() {

        when(ordersRepository.save(any())).thenReturn(order);
        when(ordersRepository.getOrderByOrderId(any())).thenReturn(Optional.of(ordersResponseDto));
        OrdersResponseDto result = ordersService.createOrder(createRequestDto);

        verify(ordersRepository, times(1)).save(any());
        assertNotNull(result);
        assertEquals("orderId", result.getOrderId());
        assertEquals("bookTitle", result.getBookTitle());
        assertEquals(Orders.OrderState.WAITING, result.getOrderState());
    }

    @Test
    @DisplayName("주문 생성 - 실패: 주문 상태 오류")
    void testCreateOrder_Fail_OrderStatusFailedException() {
        assertThrows(OrderStatusFailedException.class, () -> ordersService.createOrder(createRequestDtoNoState));
    }
    @Test
    @DisplayName("주문 생성 - 실패: 주문 저장 실패")
    void testCreateOrder_Fail_SaveOrderFailed() {
        doThrow(SaveOrderFailed.class).when(ordersRepository).save(any());

        assertThrows(SaveOrderFailed.class, () -> ordersService.createOrder(createRequestDto));


    }

    @Test
    @DisplayName("주문 상태 수정 - 성공")
    void testModifyOrderState_OrderExists() {

        String orderId = "orderId";
        Orders.OrderState newState = Orders.OrderState.COMPLETED;

        when(ordersRepository.findById(orderId)).thenReturn(Optional.of(order));

        ordersService.modifyOrderState(orderId, newState);
        ArgumentCaptor<Orders> ordersCaptor = ArgumentCaptor.forClass(Orders.class);
        verify(ordersRepository, times(1)).save(ordersCaptor.capture());
        Orders savedOrder = ordersCaptor.getValue();

        assertEquals(newState, savedOrder.getOrderState());
    }

    @Test
    @DisplayName("주문 상태 수정 - 실패: 주문이 존재하지 않음")
    void testModifyOrderState_OrderNotExists() {

        String orderId = "nonExistentOrderId";
        Orders.OrderState newState = Orders.OrderState.COMPLETED;

        when(ordersRepository.findById(orderId)).thenReturn(Optional.empty());
        assertThrows(NotFoundOrderException.class, () -> ordersService.modifyOrderState(orderId, newState));

        verify(ordersRepository, never()).save(any());
    }
}
