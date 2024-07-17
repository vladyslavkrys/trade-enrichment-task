package com.verygoodbank.tes.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.verygoodbank.tes.model.Trade;
import com.verygoodbank.tes.service.ProductService;
import com.verygoodbank.tes.service.TradeEnrichmentService;
import com.verygoodbank.tes.web.dto.TradeResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TradeEnrichmentServiceImpl implements TradeEnrichmentService {

    ProductService productService;

    // translate product_id into product_name
    // date is a valid date in yyyyMMdd format, otherwise discard the row and log an error.
    // product name is not available, the service should log the
    //          missing mapping and set the product Name as: Missing Product Name
    @Override
    public List<TradeResponse> enrichTradeData(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            List<TradeResponse> trades = reader.lines()
                    // skip header
                    .skip(1)
                    .map(this::splitLine)
                    .filter(this::isValidLine)
                    .map(this::mapPartsToTrade)
                    .collect(Collectors.toList());
            log.info("Enriched {} trades.", trades.size());
            return trades;
        }
    }

    private String[] splitLine(String line) {
        return line.split(",");
    }

    private boolean isValidLine(String[] parts) {
        String date = parts[0];
        boolean isValid = isValidDate(date);
        if (!isValid) {
            log.error("Invalid date format: {}", date);
        }
        return isValid;
    }

    // may be moved to mapper
    private TradeResponse mapPartsToTrade(String[] parts) {
        String date = parts[0];
        Long productId = Long.parseLong(parts[1]);
        String currency = parts[2];
        Double price = Double.parseDouble(parts[3]);
        return TradeResponse.builder()
                .withDate(date)
                .withProductName(productService.getProductName(productId))
                .withCurrency(currency)
                .withPrice(price)
                .build();
    }


    // may be moved to dateValidator
    private boolean isValidDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            log.error("Date parsing failed for: {}", date, e);
            return false;
        }
    }
}


