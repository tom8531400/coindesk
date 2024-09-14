package com.example.coindesksprigboot.service.impl;

import com.example.coindesksprigboot.dto.CurrencyDto;
import com.example.coindesksprigboot.repository.CurrencyRepository;
import com.example.coindesksprigboot.service.CurrencyService;
import com.example.coindesksprigboot.vo.CurrencyVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CurrencyServiceImpl implements CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public CurrencyDto addCurrency(CurrencyVo currencyVo) throws Exception {
        if (currencyVo == null) {
            log.info("addCurrency: 接收到空的 CurrencyVo");
            throw new Exception("CurrencyVo 不能為空");
        }
        try {
            CurrencyVo currency = CurrencyVo.builder()
                    .code(currencyVo.getCode())
                    .name(currencyVo.getName())
                    .build();

            CurrencyVo savedCurrency = currencyRepository.save(currency);

            return CurrencyDto.builder()
                    .id(savedCurrency.getId())
                    .code(savedCurrency.getCode())
                    .name(savedCurrency.getName())
                    .build();
        } catch (Exception ex) {
            log.info("保存幣種時發生錯誤: {}", ex.getMessage());
            throw new RuntimeException("保存幣種失敗，請稍後再試");
        }
    }

    @Override
    public List<CurrencyDto> queryAll() {
        try {
            List<CurrencyVo> currencyVoList = currencyRepository.findAll();
            log.info("查詢到的結果: {}", currencyVoList);
            List<CurrencyDto> result = currencyVoList.stream().map(o -> CurrencyDto.builder()
                            .id(o.getId())
                            .code(o.getCode())
                            .name(o.getName()).build())
                    .collect(Collectors.toList());
            log.info("Dto轉換結果: {}", result);
            return result;
        } catch (Exception ex) {
            log.info("查詢全部幣種時發生錯誤: {}", ex.getMessage());
            throw new RuntimeException("查詢全部幣種失敗，請稍後再試");
        }
    }

    @Override
    public CurrencyDto findNameByCurrency(String name) throws Exception {
        if ("".equals(name)) {
            log.info("name: {}", name);
            throw new Exception("name 不能為'' ");
        }
        try {
            CurrencyVo currencyVo = currencyRepository.findByName(name);
            log.info("查詢到的結果: {}", currencyVo);
            CurrencyDto result = CurrencyDto.builder()
                    .id(currencyVo.getId())
                    .code(currencyVo.getCode())
                    .name(currencyVo.getName())
                    .build();
            log.info("Dto轉換結果: {}", result);
            return result;
        } catch (Exception ex) {
            log.info("查詢個別幣種時發生錯誤: {}", ex.getMessage());
            throw new RuntimeException("查詢個別幣種失敗，請稍後再試");
        }
    }

    @Override
    public CurrencyDto updateCurrency(Long id, CurrencyVo currencyVo) throws Exception {
        try {
            Optional<CurrencyVo> repositoryById = currencyRepository.findById(id);
            CurrencyVo currency = repositoryById.get();
            log.info("查到的幣種為: {}", currency);
            if (currency == null) {
                log.info("幣種不存在: {}", currency);
                throw new Exception("id不存在，輸入錯誤");
            } else {
                currencyVo.setId(id);
                CurrencyVo updateCurrency = currencyRepository.save(currencyVo);
                log.info("幣種已更新: {}", updateCurrency);

                return CurrencyDto.builder()
                        .id(updateCurrency.getId())
                        .code(updateCurrency.getCode())
                        .name(updateCurrency.getName()).build();
            }
        } catch (Exception ex) {
            log.info("修改個別幣種時發生錯誤: {}", ex.getMessage());
            throw new RuntimeException("修改個別幣種失敗，請稍後再試");
        }
    }

    @Override
    public void deleteCurrency(Long id) {
        log.info("想刪除的id: {}", id);
        try {
            Optional<CurrencyVo> repositoryById = currencyRepository.findById(id);
            CurrencyVo currencyVo = repositoryById.get();
            if (currencyVo == null) {
                log.info("幣種不存在: {}", currencyVo);
                throw new Exception("id不存在，輸入錯誤");
            }
            currencyRepository.deleteById(id);
        } catch (Exception ex) {
            log.info("刪除個別幣種時發生錯誤: {}", ex.getMessage());
            throw new RuntimeException("刪除個別幣種失敗，請稍後再試");
        }
    }
}
