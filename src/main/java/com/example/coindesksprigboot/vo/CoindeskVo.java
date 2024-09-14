package com.example.coindesksprigboot.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoindeskVo {

    private String code;
    private String description;
    private String rate;
    private String updatedAt;
}
