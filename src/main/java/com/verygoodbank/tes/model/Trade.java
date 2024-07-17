package com.verygoodbank.tes.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Trade {
    String date;
    Long productId;
    String currency;
    Double price;
    String productName;
}
