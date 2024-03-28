package com.nhnacademy.delivery.wrap.dto;

import com.nhnacademy.delivery.wrap.domain.Wrap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
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
