package com.nhnacademy.delivery.customer.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * 고객(Customer) 테이블.
 *
 * @author : 박동희
 * @date : 2024-04-01
 *
 **/
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @Column(name = "customer_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerNo;
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "customer_password")
    private String customerPassword;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "customer_phone_number")
    private String customerPhoneNumber;
    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate customerBirthday;

    @Column(name = "customer_role")
    private String  customerRole;
}