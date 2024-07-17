package com.verygoodbank.tes.web.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Builder(setterPrefix = "with")
public class TradeResponse {
    @CsvBindByName(column = "date")
    String date;
    @CsvBindByName(column = "product_name")
    String productName;
    @CsvBindByName(column = "currency")
    String currency;
    @CsvBindByName(column = "price")
    Double price;
}
