package com.hirundo.libs.data_structures;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import java.time.LocalDateTime;

public class CsvBirdData {
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

    @CsvBindByName(column = "Sex")
    @CsvBindByPosition(position = 3)
    public String Sex;

    @CsvBindByName(column = "Age")
    @CsvBindByPosition(position = 4)
    public String Age;


    @CsvBindByName(column = "Weight")
    @CsvBindByPosition(position = 5)
    public Double Weight;

    @CsvBindByName(column = "Fat")
    @CsvBindByPosition(position = 6)
    public Double Fat;

    @CsvBindByName(column = "Wing")
    @CsvBindByPosition(position = 7)
    public Double Wing;

    @CsvBindByName(column = "Tail")
    @CsvBindByPosition(position = 8)
    public Double Tail;

    @CsvBindByName(column = "D2")
    @CsvBindByPosition(position = 9)
    public Double D2;

    @CsvBindByName(column = "D3")
    @CsvBindByPosition(position = 10)
    public Double D3;

    @CsvBindByName(column = "D4")
    @CsvBindByPosition(position = 11)
    public Double D4;

    @CsvBindByName(column = "D5")
    @CsvBindByPosition(position = 12)
    public Double D5;

    @CsvBindByName(column = "D6")
    @CsvBindByPosition(position = 13)
    public Double D6;

    @CsvBindByName(column = "D7")
    @CsvBindByPosition(position = 14)
    public Double D7;

    @CsvBindByName(column = "D8")
    @CsvBindByPosition(position = 15)
    public Double D8;

    public CsvBirdData(Integer id, String ring, LocalDateTime date, String sex,
                       String age, Double weight, Double fat, Double wing, Double tail,
                       Double d2, Double d3, Double d4, Double d5, Double d6, Double d7, Double d8) {
        Id = id;
        Ring = ring;
        Date = date;
        Sex = sex;
        Age = age;
        Weight = weight;
        Fat = fat;
        Wing = wing;
        Tail = tail;
        D2 = d2;
        D3 = d3;
        D4 = d4;
        D5 = d5;
        D6 = d6;
        D7 = d7;
        D8 = d8;

    }
}
