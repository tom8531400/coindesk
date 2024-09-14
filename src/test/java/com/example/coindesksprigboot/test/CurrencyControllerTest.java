package com.example.coindesksprigboot.test;

import com.example.coindesksprigboot.controller.CurrencyController;
import com.example.coindesksprigboot.dto.CurrencyDto;
import com.example.coindesksprigboot.response.ApiResponse;
import com.example.coindesksprigboot.response.ApiResponseEnum;
import com.example.coindesksprigboot.service.CurrencyService;
import com.example.coindesksprigboot.vo.CurrencyVo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CurrencyControllerTest {
    @InjectMocks
    private CurrencyController currencyController;
    @Mock
    private CurrencyService currencyService;

    @Test
    public void addCurrency() throws Exception {
        CurrencyVo currencyVo = CurrencyVo.builder()
                .id(1L)
                .code("THB")
                .name("泰銖")
                .build();

        CurrencyDto currencyDto = CurrencyDto.builder()
                .id(1L)
                .code("THB")
                .name("泰銖")
                .build();

        when(currencyService.addCurrency(any(CurrencyVo.class))).thenReturn(currencyDto);

        ApiResponse<CurrencyDto> response = currencyController.addCurrency(currencyVo);

        assertEquals(ApiResponseEnum.SUCCESS, response.getCode());
        assertEquals(currencyDto, response.getData());
    }

    @Test
    public void queryAll() {
        List<CurrencyDto> currencyDtoList = new ArrayList<>();
        currencyDtoList.add(CurrencyDto.builder().id(1L).code("THB").name("泰銖").build());
        currencyDtoList.add(CurrencyDto.builder().id(2L).code("USD").name("美元").build());

        when(currencyService.queryAll()).thenReturn(currencyDtoList);

        ApiResponse<List<CurrencyDto>> response = currencyController.queryAll();

        assertEquals(ApiResponseEnum.SUCCESS, response.getCode());
        assertEquals(currencyDtoList, response.getData());
    }

    @Test
    public void queryCurrency() throws Exception {
        // Arrange
        String name = "泰銖";
        CurrencyDto currencyDto = CurrencyDto.builder()
                .id(1L)
                .code("THB")
                .name(name)
                .build();

        when(currencyService.findNameByCurrency(name)).thenReturn(currencyDto);

        ApiResponse<CurrencyDto> response = currencyController.queryCurrency(name);

        assertEquals(ApiResponseEnum.SUCCESS, response.getCode());
        assertEquals(currencyDto, response.getData());
    }

    @Test
    public void updateCurrency() throws Exception {
        CurrencyVo currencyVo = CurrencyVo.builder()
                .id(1L)
                .code("THB")
                .name("泰銖")
                .build();

        CurrencyDto updatedCurrencyDto = CurrencyDto.builder()
                .id(1L)
                .code("THB")
                .name("泰銖")
                .build();

        when(currencyService.updateCurrency(1L, currencyVo)).thenReturn(updatedCurrencyDto);
        ApiResponse<CurrencyDto> response = currencyController.updateCurrency(1L, currencyVo);

        assertEquals(ApiResponseEnum.SUCCESS, response.getCode());
        assertEquals(updatedCurrencyDto, response.getData());
    }

    @Test
    public void deleteCurrency() {
        Long id = 1L;
        doNothing().when(currencyService).deleteCurrency(id);

        ApiResponse<Void> response = currencyController.deleteCurrency(id);

        assertEquals(ApiResponseEnum.SUCCESS, response.getCode());
        assertNull(response.getData());
    }
}
