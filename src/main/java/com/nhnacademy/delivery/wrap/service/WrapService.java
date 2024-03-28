package com.nhnacademy.delivery.wrap.service;


import com.nhnacademy.delivery.wrap.dto.ModifyWrapRequestDto;
import com.nhnacademy.delivery.wrap.dto.WrapRequestDto;
import com.nhnacademy.delivery.wrap.dto.WrapResponseDto;

import java.util.List;


public interface WrapService {
    List<WrapResponseDto> getWraps();
    WrapResponseDto getWrapById(Long wrapId);
    WrapResponseDto getWrapByName(String wrapName);
    WrapResponseDto saveWrap(WrapRequestDto wrappingRequestDto);
    WrapResponseDto modifyWrap(ModifyWrapRequestDto modifyWrapRequestDto);
    void deleteWrapById(Long wrapId);
    void deleteWrapByName(String wrapName);

}
