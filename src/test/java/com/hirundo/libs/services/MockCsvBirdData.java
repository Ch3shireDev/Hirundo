package com.hirundo.libs.services;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import java.time.LocalDateTime;

public class MockCsvBirdData {
    @CsvBindByName(column = "Id")
    @CsvBindByPosition(position = 0)
    public Integer Id;
    @CsvBindByName(column = "Ring")
    @CsvBindByPosition(position = 1)
    public String Ring;
    @CsvBindByName(column = "Date")
    @CsvBindByPosition(position = 2)
    @CsvDate("yyyy-MM-dd")
    public LocalDateTime Date;

    public MockCsvBirdData(Integer id, String ring, LocalDateTime date) {
        Id = id;
        Ring = ring;
        Date = date;
    }
}
