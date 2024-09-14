package com.example.coindesksprigboot.service.impl;

import com.example.coindesksprigboot.dto.CurrencyMapDto;
import com.example.coindesksprigboot.service.CoindeskService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Log4j2
@SuppressWarnings("all")
public class CoindeskServiceImpl implements CoindeskService {
    private static final String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static DateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss z", Locale.ENGLISH);

    @Override
    public Map<String, Object> getCoindeskApi() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            log.info("response: {}", response);
            return response;
        } catch (Exception ex) {
            log.info("連接api時發生錯誤: {}", ex.getMessage());
            throw new RuntimeException("連接api時失敗，請稍後再試");
        }
    }

    @Override
    public Map<String, Map<String, Object>> getCoindesk() {
        Map<String, Map<String, Object>> result = new LinkedHashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            log.info("response: {}", response);
            if (response == null) {
                throw new RuntimeException("查詢連結錯誤，請稍後再試");
            }
            Map<String, Object> time = (Map<String, Object>) response.get("time");
            String updatedStr = time.get("updated").toString();
            Date date = inputFormat.parse(updatedStr);
            String updated = dateFormat.format(date);
            Map map = new HashMap();
            map.put("updated", updated);
            result.put("time", map);

            Map<String, Object> bpi = (Map<String, Object>) response.get("bpi");
            Map<String, Object> maps = new HashMap<>();
            for (Map.Entry<String, Object> o : bpi.entrySet()) {
                Map<String, Object> value = (Map<String, Object>) o.getValue();
                maps.put(o.getKey(), CurrencyMapDto.builder()
                        .code(value.get("code").toString())
                        .description(value.get("description").toString())
                        .rate(value.get("rate").toString()).build());
            }
            result.put("bpi", maps);
            log.info("新的api回傳訊息: {}", result);
            return result;
        } catch (Exception ex) {
            log.info("連接api時發生錯誤: {}", ex.getMessage());
            throw new RuntimeException("連接api時失敗，請稍後再試");
        }
    }
}
