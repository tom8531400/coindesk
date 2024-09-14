package com.example.coindesksprigboot.controller;

import com.example.coindesksprigboot.dto.CurrencyDto;
import com.example.coindesksprigboot.response.ApiResponse;
import com.example.coindesksprigboot.response.ApiResponseEnum;
import com.example.coindesksprigboot.service.CurrencyService;
import com.example.coindesksprigboot.vo.CurrencyVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 幣別 DB 維護功能。
 */
@RestController
@RequestMapping(value = "/currency")
@Log4j2
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    /**
     * 新增幣種
     *
     * @param currencyVo 幣種資訊
     * @return 新增幣種資訊
     * @throws Exception ex
     */
    @RequestMapping(value = "/addCurrency", method = RequestMethod.POST)
    public ApiResponse<CurrencyDto> addCurrency(@RequestBody CurrencyVo currencyVo) {
        log.info("存入幣種資訊: {}", currencyVo);
        try {
            CurrencyDto currency = currencyService.addCurrency(currencyVo);
            if (currency != null) {
                return ApiResponse.success(currency);
            } else {
                return ApiResponse.error(ApiResponseEnum.BAD_REQUEST, "新增幣種錯誤");
            }
        } catch (Exception ex) {
            log.info("addCurrency方法執行錯誤訊息: {}", ex.getMessage());
            return ApiResponse.error(ApiResponseEnum.INTERNAL_SERVER_ERROR, "新增幣種失敗");
        }
    }

    /**
     * 查詢全部幣種資訊
     *
     * @return 全部幣種
     */
    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    public ApiResponse<List<CurrencyDto>> queryAll() {
        log.info("查詢全部currency..");
        try {
            List<CurrencyDto> result = currencyService.queryAll();
            return ApiResponse.success(result);
        } catch (Exception ex) {
            log.info("queryAll方法執行錯誤訊息: {}", ex.getMessage());
            return ApiResponse.error(ApiResponseEnum.INTERNAL_SERVER_ERROR, "查詢全部幣種失敗");
        }
    }

    /**
     * 查詢個別幣種資訊
     *
     * @param name 幣種名稱
     * @return 個別幣種資訊
     */
    @RequestMapping(value = "queryCurrency", method = RequestMethod.GET)
    public ApiResponse<CurrencyDto> queryCurrency(@RequestParam(value = "name") String name) {
        log.info("欲查幣種名稱: {}", name);
        try {
            CurrencyDto nameByCurrency = currencyService.findNameByCurrency(name);
            return ApiResponse.success(nameByCurrency);
        } catch (Exception ex) {
            log.info("queryCurrency方法執行錯誤訊息: {}", ex.getMessage());
            return ApiResponse.error(ApiResponseEnum.INTERNAL_SERVER_ERROR, "查詢個別幣種失敗");
        }
    }

    /**
     * 修改幣種資訊
     *
     * @param id         幣種id
     * @param currencyVo 幣種資訊
     * @return 修改後幣種資訊
     */
    @RequestMapping(value = "/updateCurrency/{id}", method = RequestMethod.PUT)
    public ApiResponse<CurrencyDto> updateCurrency(@PathVariable Long id, @RequestBody CurrencyVo currencyVo) {
        log.info("欲修改的id: {}, 欲修改的內容: {}", id, currencyVo);
        try {
            CurrencyDto currency = currencyService.updateCurrency(id, currencyVo);
            return ApiResponse.success(currency);
        } catch (Exception ex) {
            log.info("updateCurrency方法執行錯誤訊息: {}", ex.getMessage());
            return ApiResponse.error(ApiResponseEnum.INTERNAL_SERVER_ERROR, "修改幣種失敗");
        }
    }

    /**
     * @param id 欲刪除幣種id
     * @return 刪除結果
     */
    public ApiResponse<Void> deleteCurrency(@PathVariable Long id) {
        log.info("欲刪除的id: {}", id);
        try {
            currencyService.deleteCurrency(id);
            return ApiResponse.success(null);
        } catch (Exception ex) {
            log.info("deleteCurrency方法執行錯誤訊息: {}", ex.getMessage());
            return ApiResponse.error(ApiResponseEnum.INTERNAL_SERVER_ERROR, "刪除幣種失敗");
        }
    }
}
