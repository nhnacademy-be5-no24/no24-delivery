package com.nhnacademy.delivery.wrap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.delivery.wrap.controller.WrapController;
import com.nhnacademy.delivery.wrap.dto.ModifyWrapRequestDto;
import com.nhnacademy.delivery.wrap.dto.WrapRequestDto;
import com.nhnacademy.delivery.wrap.dto.WrapResponseDto;
import com.nhnacademy.delivery.wrap.exception.AlreadyExistWrapException;
import com.nhnacademy.delivery.wrap.exception.NotFoundWrapException;
import com.nhnacademy.delivery.wrap.exception.NotFoundWrapNameException;
import com.nhnacademy.delivery.wrap.service.WrapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WrapController.class)
class WrapControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    WrapService wrapService;
    ObjectMapper objectMapper = new ObjectMapper();
    WrapRequestDto wrapRequestDto;
    ModifyWrapRequestDto modifyWrapRequestDto;
    WrapResponseDto wrapResponseDto;

    @BeforeEach
    void setUp(){
        wrapRequestDto = WrapRequestDto.builder()
                .wrapName("wrap1").wrapCost(1L).build();
        wrapResponseDto = WrapResponseDto.builder()
                .wrapId(1L).wrapName("wrap1").wrapCost(1L).build();
    }
    @Test
    void testGetWraps_ReturnsPageOfWrapResponseDto() throws Exception {

        List<WrapResponseDto> wrapResponseDtos = Collections.singletonList(wrapResponseDto);
        Page<WrapResponseDto> wrapResponseDtoPage = new PageImpl<>(wrapResponseDtos);

        when(wrapService.getWraps(any(Pageable.class))).thenReturn(wrapResponseDtoPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/delivery/wraps")
                        .param("page", "0")  // 페이지 번호
                        .param("size", "10")) // 페이지 크기
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].wrapId").value(wrapResponseDto.getWrapId()))
                .andExpect(jsonPath("$.content[0].wrapName").value(wrapResponseDto.getWrapName()))
                .andExpect(jsonPath("$.content[0].wrapCost").value(wrapResponseDto.getWrapCost()));
    }


    @Test
    void testGetWrapById_ReturnsWrapResponseDto() throws Exception {
        Long wrapId = 1L;
        when(wrapService.getWrapById(wrapId)).thenReturn(wrapResponseDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/delivery/wraps/id/{wrapId}", wrapId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.wrapId").value(wrapResponseDto.getWrapId()));
    }
    @Test
    void testGetWrapById_ReturnsNotFoundStatus() throws Exception {
        Long wrapId = 1L;
        when(wrapService.getWrapById(wrapId)).thenThrow(NotFoundWrapException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/delivery/wraps/id/{wrapId}", wrapId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetWrapByName_ReturnsWrapResponseDto() throws Exception{
        String wrapName = "wrap1";
        when(wrapService.getWrapByName(wrapName)).thenReturn(wrapResponseDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/delivery/wraps/name/{wrapName}", wrapName)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.wrapId").value(wrapResponseDto.getWrapId()));
    }
    @Test
    void testGetWrapByName_ReturnsNotFoundStatus() throws Exception{
        String wrapName = "wrap1";
        when(wrapService.getWrapByName(wrapName)).thenThrow(NotFoundWrapNameException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/delivery/wraps/name/{wrapName}", wrapName)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    void testSaveWrap_ReturnsCreated() throws Exception {
        when(wrapService.saveWrap(wrapRequestDto)).thenReturn(wrapResponseDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/delivery/wraps")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wrapRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.wrapId").value(wrapResponseDto.getWrapId()))
                .andExpect(jsonPath("$.wrapName").value(wrapResponseDto.getWrapName()));
    }
    @Test
    void testSaveWrap_ReturnsNotFound() throws Exception{
        when(wrapService.saveWrap(wrapRequestDto)).thenThrow(AlreadyExistWrapException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/delivery/wraps")
                        .content(objectMapper.writeValueAsString(wrapRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    void testModifyWrap_ReturnsWrapResponseDto() throws Exception{
        modifyWrapRequestDto = ModifyWrapRequestDto.builder()
                        .wrapId(1L).wrapName("wrap1").wrapCost(1L).build();
        when(wrapService.modifyWrap(modifyWrapRequestDto)).thenReturn(wrapResponseDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/delivery/wraps/")
                        .content(objectMapper.writeValueAsString(modifyWrapRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void testModifyWarp_ReturnsNotFound() throws Exception{
        modifyWrapRequestDto = ModifyWrapRequestDto.builder()
                .wrapId(1L).wrapName("wrap1").wrapCost(1L).build();
        when(wrapService.modifyWrap(modifyWrapRequestDto)).thenThrow(NotFoundWrapException.class);

        mockMvc.perform(MockMvcRequestBuilders.put("/delivery/wraps/")
                        .content(objectMapper.writeValueAsString(modifyWrapRequestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    void testDeleteById_ReturnsOkStatus() throws Exception{
        Long wrapId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/delivery/wraps/id/{wrapId}", wrapId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void testDeleteById_ReturnsNotFound() throws Exception{
        Long wrapId = 1L;
        doThrow(NotFoundWrapException.class).when(wrapService).deleteWrapById(wrapId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/delivery/wraps/id/{wrapId}", wrapId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    void testDeleteByName_ReturnsOkStatus() throws Exception{
        String wrapName = "existingWrapName";

        mockMvc.perform(MockMvcRequestBuilders.delete("/delivery/wraps/name/{wrapName}", wrapName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(wrapService, times(1)).deleteWrapByName(wrapName);
    }
    @Test
    void deleteByName_ReturnsNotFoundStatus() throws Exception {
        String wrapName = "nonExistingWrapName";
        doThrow(NotFoundWrapNameException.class).when(wrapService).deleteWrapByName(wrapName);

        mockMvc.perform(MockMvcRequestBuilders.delete("/delivery/wraps/name/{wrapName}", wrapName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
