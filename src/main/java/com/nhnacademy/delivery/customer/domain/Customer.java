package com.nhnacademy.delivery.customer.domain;


import lombok.*;

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
@Data
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
    private LocalDate customerBirthday;
    @Column(name = "customer_role")
    private String  customerRole;
}