package com.nhnacademy.delivery.wrap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/**
 * 포장 수정을 위한 request dto 입니다.
 *
 * @author : 박동희
 * @date : 2024-03-29
 **/

@AllArgsConstructor
@Builder
public class ModifyWrapRequestDto {
    private Long wrapId;
    private String wrapName;
    private Long wrapCost;
}
