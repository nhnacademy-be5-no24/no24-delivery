package com.nhnacademy.delivery.wrap.dto;

import com.nhnacademy.delivery.wrap.domain.Wrap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;


/**
 * 포장 기본정보를 반환하기 위한 response dto 입니다.
 *
 * @author : 박동희
 * @date : 2024-03-29
 **/
@Getter
@AllArgsConstructor
@Builder
public class WrapResponseDto {
    private Long wrapId;
    private String wrapName;
    private Long wrapCost;

    public WrapResponseDto(Wrap wrap){
        this.wrapId = wrap.getWrapId();
        this.wrapName = wrap.getWrapName();
        this.wrapCost = wrap.getWrapCost();
    }
}
