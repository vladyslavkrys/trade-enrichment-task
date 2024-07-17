package com.verygoodbank.tes.mapper.impl;

import java.io.StringWriter;
import java.util.List;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.verygoodbank.tes.mapper.CsvMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CsvMapperImpl<T> implements CsvMapper<T> {
    @Override
    public String map(List<T> list) {
        StringWriter writer = new StringWriter();
        try {
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer).build();
            beanToCsv.write(list);
        } catch (Exception e) {
            log.error("Error during transformation object into CSV.", e);
            throw new RuntimeException("Error during transformation object into CSV.", e);
        }

        return writer.toString();
    }
}
