package com.example.coindesksprigboot.service;

import com.example.coindesksprigboot.dto.CurrencyDto;
import com.example.coindesksprigboot.vo.CurrencyVo;

import java.util.List;

public interface CurrencyService {

    CurrencyDto addCurrency(CurrencyVo currencyVo) throws Exception;

    List<CurrencyDto> queryAll();

    CurrencyDto findNameByCurrency(String name) throws Exception;

    CurrencyDto updateCurrency(Long id, CurrencyVo currencyVo) throws Exception;

    void deleteCurrency(Long id);
}
