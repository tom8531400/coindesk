package com.example.coindesksprigboot.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class CoindeskDto {

    private String updatedAt;
    private Map<String, Map<String, Object>> currencyMapDto;
}
