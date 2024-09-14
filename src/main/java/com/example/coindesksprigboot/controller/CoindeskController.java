package com.example.coindesksprigboot.controller;

import com.example.coindesksprigboot.response.ApiResponse;
import com.example.coindesksprigboot.response.ApiResponseEnum;
import com.example.coindesksprigboot.service.CoindeskService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/coindesk")
@Log4j2
public class CoindeskController {
    @Autowired
    private CoindeskService coindeskService;

    /**
     * 呼叫 coindeskAPI
     *
     * @return coindeskAPI資訊
     */
    @RequestMapping(value = "/getCoindeskApi", method = RequestMethod.GET)
    public ApiResponse<Map<String, Object>> getCoindeskApi() {
        log.info("準備連接url..");
        Map<String, Object> response = coindeskService.getCoindeskApi();
        return ApiResponse.success(response);
    }


    /**
     * 呼叫資料轉換的API
     *
     * @return 轉換後API資訊
     */
    @RequestMapping(value = "/getCurrentPrice", method = RequestMethod.GET)
    public ApiResponse<Map<String, Map<String, Object>>> getCurrentPrice() {
        log.info("準備連接url..");
        try {
            Map<String, Map<String, Object>> response = coindeskService.getCoindesk();
            log.info("response: {}", response);
            return ApiResponse.success(response);
        } catch (Exception ex) {
            log.info("getCurrentPrice方法執行錯誤訊息: {}", ex.getMessage());
            return ApiResponse.error(ApiResponseEnum.INTERNAL_SERVER_ERROR, "查詢API錯誤");
        }
    }
}
