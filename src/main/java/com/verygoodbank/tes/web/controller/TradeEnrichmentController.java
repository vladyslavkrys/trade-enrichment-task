package com.verygoodbank.tes.web.controller;

import java.util.List;

import com.verygoodbank.tes.mapper.CsvMapper;
import com.verygoodbank.tes.service.TradeEnrichmentService;
import com.verygoodbank.tes.web.dto.TradeResponse;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TradeEnrichmentController {

    TradeEnrichmentService tradeEnrichmentService;
    CsvMapper<TradeResponse> csvMapper;

    // swagger can be added for API docs
    // pagination can be used
    // add proper validation for input
    @PostMapping(value = "/enrich", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "text/csv")
    public ResponseEntity<String> enrichTradeData(@RequestParam("file") MultipartFile file) {
        try {
            List<TradeResponse> trades = tradeEnrichmentService.enrichTradeData(file);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "text/csv")
                    .body(csvMapper.map(trades));
        } catch (Exception e) {
            // aop logging may be added (also can track execution of each method)
            log.error("Failed to enrich trades with product names: ", e);
            // custom exception may be added
            throw new RuntimeException("Failed to enrich trades with product names", e);
        }
    }

}
