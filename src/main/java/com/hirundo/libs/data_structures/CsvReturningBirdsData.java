package com.hirundo.libs.data_structures;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class CsvReturningBirdsData {
    @CsvBindByName(column = "RingNumber")
    @CsvBindByPosition(position = 0)
    public String RingNumber;
    @CsvBindByName(column = "Species")
    @CsvBindByPosition(position = 1)
    public String Species;
    @CsvBindByName(column = "FirstDateSeen")
    @CsvBindByPosition(position = 2)
    @CsvDate("yyyy-MM-dd")
    public LocalDateTime FirstDateSeen;
    @CsvBindByName(column = "LastDateSeen")
    @CsvBindByPosition(position = 3)
    @CsvDate("yyyy-MM-dd")
    public LocalDateTime LastDateSeen;
    @CsvBindByName(column = "FirstSeasonSeen")
    @CsvBindByPosition(position = 4)
    public String FirstSeasonSeen;
    @CsvBindByName(column = "LastSeasonSeen")
    @CsvBindByPosition(position = 5)
    public String LastSeasonSeen;
    @CsvBindByName(column = "RecordDate")
    @CsvBindByPosition(position = 6)
    @CsvDate("yyyy-MM-dd")
    public LocalDateTime RecordDate;
    @CsvBindByName(column = "Sex")
    @CsvBindByPosition(position = 7)
    public String Sex;
    @CsvBindByName(column = "Age")
    @CsvBindByPosition(position = 8)
    public String Age;
    @CsvBindByName(column = "Weight")
    @CsvBindByPosition(position = 9)
    public BigDecimal Weight;
    @CsvBindByName(column = "Fat")
    @CsvBindByPosition(position = 10)
    public Integer Fat;
    @CsvBindByName(column = "Wing")
    @CsvBindByPosition(position = 11)
    public BigDecimal Wing;
    @CsvBindByName(column = "Tail")
    @CsvBindByPosition(position = 12)
    public Integer Tail;
    @CsvBindByName(column = "D2")
    @CsvBindByPosition(position = 13)
    public Integer D2;
    @CsvBindByName(column = "D3")
    @CsvBindByPosition(position = 14)
    public Integer D3;
    @CsvBindByName(column = "D4")
    @CsvBindByPosition(position = 15)
    public Integer D4;
    @CsvBindByName(column = "D5")
    @CsvBindByPosition(position = 16)
    public Integer D5;
    @CsvBindByName(column = "D6")
    @CsvBindByPosition(position = 17)
    public Integer D6;
    @CsvBindByName(column = "D7")
    @CsvBindByPosition(position = 18)
    public Integer D7;
    @CsvBindByName(column = "D8")
    @CsvBindByPosition(position = 19)
    public Integer D8;


}
