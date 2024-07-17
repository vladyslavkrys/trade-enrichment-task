package com.verygoodbank.tes.mapper;

import java.util.List;

public interface CsvMapper<T> {
    String map(List<T> object);
}
