package com.nhnacademy.delivery.wrap.service.impl;

import com.nhnacademy.delivery.wrap.domain.Wrap;
import com.nhnacademy.delivery.wrap.dto.ModifyWrapRequestDto;
import com.nhnacademy.delivery.wrap.dto.WrapRequestDto;
import com.nhnacademy.delivery.wrap.dto.WrapResponseDto;
import com.nhnacademy.delivery.wrap.exception.AlreadyExistWrapException;
import com.nhnacademy.delivery.wrap.exception.NotFoundWrapException;
import com.nhnacademy.delivery.wrap.exception.NotFoundWrapNameException;
import com.nhnacademy.delivery.wrap.repository.WrapRepository;
import com.nhnacademy.delivery.wrap.service.WrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WrapServiceImpl implements WrapService {
    private WrapRepository wrapRepository;
    @Override
    @Transactional(readOnly = true)
    public List<WrapResponseDto> getWraps() {
        List<Wrap> wraps = wrapRepository.findAll();
        List<WrapResponseDto> dtos = new ArrayList<>();
        for(Wrap wrap : wraps){
            WrapResponseDto dto = new WrapResponseDto(wrap.getWrapId(), wrap.getWrapName(), wrap.getWrapCost());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public WrapResponseDto getWrapById(Long wrapId) {
        Optional<Wrap> optionalWrapping = wrapRepository.findById(wrapId);
        if(optionalWrapping.isEmpty()) {
            throw new NotFoundWrapException(wrapId);
        }
        return new WrapResponseDto(optionalWrapping.get());
    }

    @Override
    @Transactional(readOnly = true)
    public WrapResponseDto getWrapByName(String wrapName) {
        Optional<Wrap> optionalWrapping = wrapRepository.findWrapByWrapName(wrapName);
        if(optionalWrapping.isEmpty()) {
            throw new NotFoundWrapNameException(wrapName);
        }
        return new WrapResponseDto(optionalWrapping.get());
    }

    @Override
    @Transactional
    public WrapResponseDto saveWrap(WrapRequestDto wrappingRequestDto) {
        String wrapName = wrappingRequestDto.getWrapName();
        if (wrapRepository.findWrapByWrapName(wrapName).isPresent()) {
            throw new AlreadyExistWrapException(wrapName);
        }
        Wrap wrap = new Wrap(null, wrappingRequestDto.getWrapName(), wrappingRequestDto.getWrapCost());
        Wrap createdWrap = wrapRepository.save(wrap);
        return new WrapResponseDto(createdWrap);
    }

    @Override
    @Transactional
    public WrapResponseDto modifyWrap(ModifyWrapRequestDto modifyWrappingRequestDto) {
        Optional<Wrap> optionalWrap = wrapRepository.findById(modifyWrappingRequestDto.getWrapId());
        if (optionalWrap.isEmpty()) {
            throw new NotFoundWrapException(modifyWrappingRequestDto.getWrapId());
        }
        Wrap wrap = optionalWrap.get();
        Wrap updatedWrap = wrapRepository.save(wrap);
        return new WrapResponseDto(updatedWrap);
    }

    @Override
    @Transactional
    public void deleteWrapById(Long wrapId) {
        if (wrapRepository.findById(wrapId).isEmpty()){
            throw new NotFoundWrapException(wrapId);
        }
        wrapRepository.deleteById(wrapId);
    }

    @Override
    public void deleteWrapByName(String wrapName) {
        Optional<Wrap> optionalWrap = wrapRepository.findWrapByWrapName(wrapName);
        if (optionalWrap.isEmpty()) {
            throw new NotFoundWrapNameException(wrapName);
        }
        wrapRepository.delete(optionalWrap.get());
    }
}
