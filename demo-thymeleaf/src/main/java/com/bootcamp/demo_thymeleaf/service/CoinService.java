package com.bootcamp.demo_thymeleaf.service;

import java.util.List;
import com.bootcamp.demo_thymeleaf.model.dto.CoinDTO;

public interface CoinService {
    List<CoinDTO> getCoins();
    CoinDTO getCoinById(String id);
}
