package com.example.coindesksprigboot.repository;

import com.example.coindesksprigboot.vo.CurrencyVo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<CurrencyVo, Long> {

    CurrencyVo findByName(String name);
}
