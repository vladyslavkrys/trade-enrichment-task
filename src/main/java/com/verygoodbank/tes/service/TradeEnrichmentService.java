package com.verygoodbank.tes.service;

import com.verygoodbank.tes.model.Trade;
import com.verygoodbank.tes.web.dto.TradeResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TradeEnrichmentService {
    List<TradeResponse> enrichTradeData(MultipartFile file) throws Exception;
}
