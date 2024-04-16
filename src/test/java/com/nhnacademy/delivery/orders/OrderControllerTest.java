package com.nhnacademy.delivery.orders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.delivery.orders.controller.OrderController;
import com.nhnacademy.delivery.orders.dto.request.OrdersCreateRequestDto;
import com.nhnacademy.delivery.orders.dto.request.OrdersModifyOrderStateRequestDto;
import com.nhnacademy.delivery.orders.dto.response.OrdersListForAdminResponseDto;
import com.nhnacademy.delivery.orders.dto.response.OrdersResponseDto;
import com.nhnacademy.delivery.orders.service.OrdersService;
import com.nhnacademy.delivery.wrap.controller.WrapController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    OrdersService ordersService;
    private ObjectMapper objectMapper = new ObjectMapper();
    private OrdersListForAdminResponseDto ordersListForAdminResponseDto;
    private OrdersResponseDto ordersResponseDto;
    private OrdersCreateRequestDto ordersCreateRequestDto;
    private OrdersModifyOrderStateRequestDto ordersModifyOrderStateRequestDto;

}
