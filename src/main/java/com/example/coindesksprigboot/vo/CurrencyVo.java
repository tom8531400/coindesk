package com.example.coindesksprigboot.vo;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name = "CURRENCY")
public class CurrencyVo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;

    public CurrencyVo() {

    }

    public CurrencyVo(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
}
