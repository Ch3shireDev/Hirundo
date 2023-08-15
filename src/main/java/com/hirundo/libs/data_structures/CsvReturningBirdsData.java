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

    public static CsvReturningBirdsData from(ReturningBirdsData data, DbBirdRecord record) {
        var result = new CsvReturningBirdsData();
        result.RingNumber = data.RingNumber;
        result.Species = data.Species;
        result.FirstDateSeen = data.FirstDateSeen;
        result.LastDateSeen = data.LastDateSeen;
        result.FirstSeasonSeen = data.FirstSeasonSeen.toString();
        result.LastSeasonSeen = data.LastSeasonSeen.toString();
        result.RecordDate = record.getDate();
        result.Age = record.getAge();
        result.Weight = CsvReturningBirdsData.roundDecimal(record.getWeight());
        result.Fat = record.getFat();
        result.Wing = CsvReturningBirdsData.roundDecimal(record.getWing());
        result.Tail = record.getTail();

        if (record.getSex() == BirdSex.Male) result.Sex = "M";
        if (record.getSex() == BirdSex.Female) result.Sex = "F";
        if (record.getSex() == BirdSex.Undefined) result.Sex = "";

        result.D2 = record.getD2();
        result.D3 = record.getD3();
        result.D4 = record.getD4();
        result.D5 = record.getD5();
        result.D6 = record.getD6();
        result.D7 = record.getD7();
        result.D8 = record.getD8();
        return result;
    }

    static BigDecimal round(BigDecimal value) {
        if (value == null) return null;
        return value.setScale(0, RoundingMode.FLOOR);
    }

    static BigDecimal roundDecimal(BigDecimal value) {
        if (value == null) return null;
        return value.setScale(3, RoundingMode.FLOOR);
    }

}
