package com.nhnacademy.delivery.order.controller;

import com.nhnacademy.delivery.order.dto.request.OrderCreateRequestDto;
import com.nhnacademy.delivery.order.dto.request.OrderModifyOrderStateRequestDto;
import com.nhnacademy.delivery.order.dto.response.OrderListForAdminResponseDto;
import com.nhnacademy.delivery.order.dto.response.OrderResponseDto;
import com.nhnacademy.delivery.order.exception.OrderStatusFailedException;
import com.nhnacademy.delivery.order.exception.SaveOrderFailed;
import com.nhnacademy.delivery.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;


    /**
     * 모든 주문을 반환. (admin)
     * @param pageable 페이징.
     * @return 200 OK, 모든 주문 반환.
     */
    @GetMapping("/admin")
    public ResponseEntity<Page<OrderListForAdminResponseDto>> getOrders(
            Pageable pageable
    ){
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderService.getOrders(pageable));
    }
    /**
     * 고객 번호로 고객의 모든 주문을 반환.
     *
     * @param customerNo 고객 번호.
     * @param pageable 페이징.
     * @return 200, 고객의 모든 주문 반환.
     */
    @GetMapping("/customer/{customerNo}")
    public ResponseEntity<Page<OrderResponseDto>> getOrdersByCustomer(
            @PathVariable Long customerNo,
            Pageable pageable) {
        Page<OrderResponseDto> ordersPage = orderService.getOrdersByCustomer(pageable, customerNo);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ordersPage);
    }

    /**
     * 주문아이디로 주문 정보를 반환
     *
     * @param orderId 주문 아이디.
     * @return 200 OK, 주문 상세내용을 반환.
     */
    @GetMapping("/orderId/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrderDetailByOrderId(
            @PathVariable String orderId
    ) {
        OrderResponseDto responseDto = orderService.getOrderByOrderId(orderId);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseDto);
    }

    /**
     *
     * @param orderCreateRequestDto 주문 등록을 위한 dto.
     * @throws OrderStatusFailedException not found.
     * @throws SaveOrderFailed not found.
     * @return 201 created.
     */
    @PostMapping("/orders")
    public ResponseEntity<String> createOrder(
            @RequestBody OrderCreateRequestDto orderCreateRequestDto
            ){
        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(orderService.createOrder(orderCreateRequestDto).getOrderId());
        }catch(OrderStatusFailedException | SaveOrderFailed e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * 주문 상태 바꾸는 method.
     *
     * @param orderId 주문 아이디
     * @param orderModifyOrderStateRequestDto 주문상태 정보 dto
     *
     * @return 201, created 반환
     */
    @PutMapping("orders/{orderId}/state")
    public ResponseEntity<Void> modifyOrderState(
            @PathVariable String orderId,
            @RequestBody OrderModifyOrderStateRequestDto orderModifyOrderStateRequestDto
    ){
        orderService.modifyOrderState(orderId, orderModifyOrderStateRequestDto.getOrderState());
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


}
