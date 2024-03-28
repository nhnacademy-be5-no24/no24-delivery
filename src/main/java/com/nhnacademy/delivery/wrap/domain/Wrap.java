package com.nhnacademy.delivery.wrap.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wrapId;

    @Column(name = "wrap_name")
    private String wrapName;

    @Column(name = "wrap_cost")
    private Long wrapCost;

}
