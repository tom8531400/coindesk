package com.example.coindesksprigboot.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyMapDto {
    private String code;
    private String description;
    private String rate;
}
