package com.nhnacademy.delivery.wrap.controller;

import com.nhnacademy.delivery.wrap.dto.ModifyWrapRequestDto;
import com.nhnacademy.delivery.wrap.dto.WrapRequestDto;
import com.nhnacademy.delivery.wrap.dto.WrapResponseDto;
import com.nhnacademy.delivery.wrap.exception.AlreadyExistWrapException;
import com.nhnacademy.delivery.wrap.exception.NotFoundWrapException;
import com.nhnacademy.delivery.wrap.exception.NotFoundWrapNameException;
import com.nhnacademy.delivery.wrap.service.WrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/delivery")
public class WrapController {
    private final WrapService wrapService;

    @GetMapping("/wraps/")
    public ResponseEntity<List<WrapResponseDto>> getWraps(){
        return ResponseEntity.status(HttpStatus.OK).body(wrapService.getWraps());
    }
    @GetMapping("/wraps/id/{wrapId}")
    public ResponseEntity<WrapResponseDto> getWrapById(@PathVariable Long wrapId){
        try{
            WrapResponseDto wrapResponseDto = wrapService.getWrapById(wrapId);
            return ResponseEntity.ok().body(wrapResponseDto);
        }catch (NotFoundWrapException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/wraps/name/{wrapName}")
    public ResponseEntity<WrapResponseDto> getWrapByName(@PathVariable String wrapName){
        try{
            WrapResponseDto wrapResponseDto = wrapService.getWrapByName(wrapName);
            return ResponseEntity.ok().body(wrapResponseDto);
        }catch (NotFoundWrapNameException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/wraps")
    public ResponseEntity<WrapResponseDto> saveWrap(@RequestBody WrapRequestDto wrapRequestDto){
        try{
            WrapResponseDto wrapResponseDto = wrapService.saveWrap(wrapRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(wrapResponseDto);
        }catch (AlreadyExistWrapException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/wraps/")
    public ResponseEntity<WrapResponseDto> modifyWrap(@RequestBody ModifyWrapRequestDto modifyWrapRequestDto){
        try{
            WrapResponseDto wrapResponseDto = wrapService.modifyWrap(modifyWrapRequestDto);
            return ResponseEntity.ok().body(wrapResponseDto);
        }catch (NotFoundWrapException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/wraps/id/{wrapId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long wrapId){
        try {
            wrapService.deleteWrapById(wrapId);
            return ResponseEntity.ok().build();
        }catch (NotFoundWrapException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/wraps/name/{wrapName}")
    public ResponseEntity<Void> deleteByName(@PathVariable String wrapName){
        try {
            wrapService.deleteWrapByName(wrapName);
            return ResponseEntity.ok().build();
        }catch (NotFoundWrapNameException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
