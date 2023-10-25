package com.hirundo.libs.data_structures;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CsvReturningBirdsData {
    @CsvBindByName(column = "RingNumber")
    @CsvBindByPosition(position = 0)
    public String RingNumber;
    @CsvBindByName(column = "Species")
    @CsvBindByPosition(position = 1)
    public String Species;
    @CsvBindByName(column = "FirstDateSeen")
    @CsvDate("yyyy-MM-dd")
    @CsvBindByPosition(position = 2)
    public LocalDateTime FirstDateSeen;
    @CsvBindByName(column = "LastDateSeen")
    @CsvDate("yyyy-MM-dd")
    @CsvBindByPosition(position = 3)
    public LocalDateTime LastDateSeen;
    @CsvBindByName(column = "Population")
    @CsvBindByPosition(position = 4)
    public int Population;
    @CsvBindByName(column = "RecordsCount")
    @CsvBindByPosition(position = 5)
    public int RecordsCount;
    @CsvBindByName(column = "FirstSeasonSeen")
    @CsvBindByPosition(position = 6)
    public String FirstSeasonSeen;
    @CsvBindByName(column = "LastSeasonSeen")
    @CsvBindByPosition(position = 7)
    public String LastSeasonSeen;

    @CsvBindByName(column = "Sex")
    @CsvBindByPosition(position = 8)
    public String Sex;

    @CsvBindByName(column = "Age")
    @CsvBindByPosition(position = 9)
    public String Age;
    @CsvBindByName(column = "Weight")
    @CsvBindByPosition(position = 10)
    public BigDecimal Weight;
    @CsvBindByName(column = "WeightPopulationMean")
    @CsvBindByPosition(position = 11)
    public BigDecimal WeightPopulationMean;
    @CsvBindByName(column = "WeightPopulationStandardDeviation")
    @CsvBindByPosition(position = 12)
    public BigDecimal WeightPopulationStandardDeviation;
    @CsvBindByName(column = "Fat")
    @CsvBindByPosition(position = 13)
    public Integer Fat;
    @CsvBindByName(column = "FatPopulationMedian")
    @CsvBindByPosition(position = 14)
    public BigDecimal FatPopulationMedian;
    @CsvBindByName(column = "FatPopulationUpperQuartile")
    @CsvBindByPosition(position = 15)
    public BigDecimal FatPopulationUpperQuartile;
    @CsvBindByName(column = "FatPopulationLowerQuartile")
    @CsvBindByPosition(position = 16)
    public BigDecimal FatPopulationLowerQuartile;
    @CsvBindByName(column = "Wing")
    @CsvBindByPosition(position = 17)
    public BigDecimal Wing;
    @CsvBindByName(column = "WingPopulationMean")
    @CsvBindByPosition(position = 18)
    public BigDecimal WingPopulationMean;
    @CsvBindByName(column = "WingPopulationStandardDeviation")
    @CsvBindByPosition(position = 19)
    public BigDecimal WingPopulationStandardDeviation;
    @CsvBindByName(column = "Tail")
    @CsvBindByPosition(position = 20)
    public BigDecimal Tail;
    @CsvBindByName(column = "TailPopulationMean")
    @CsvBindByPosition(position = 21)
    public BigDecimal TailPopulationMean;
    @CsvBindByName(column = "TailPopulationStandardDeviation")
    @CsvBindByPosition(position = 22)
    public BigDecimal TailPopulationStandardDeviation;
    @CsvBindByName(column = "Pointedness")
    @CsvBindByPosition(position = 23)
    public BigDecimal Pointedness;
    @CsvBindByName(column = "PointednessPopulationMean")
    @CsvBindByPosition(position = 24)
    public BigDecimal PointednessPopulationMean;
    @CsvBindByName(column = "PointednessPopulationStandardDeviation")
    @CsvBindByPosition(position = 25)
    public BigDecimal PointednessPopulationStandardDeviation;
    @CsvBindByName(column = "Symmetry")
    @CsvBindByPosition(position = 26)
    public BigDecimal Symmetry;
    @CsvBindByName(column = "SymmetryPopulationMean")
    @CsvBindByPosition(position = 27)
    public BigDecimal SymmetryPopulationMean;
    @CsvBindByName(column = "SymmetryPopulationStandardDeviation")
    @CsvBindByPosition(position = 28)
    public BigDecimal SymmetryPopulationStandardDeviation;
    @CsvBindByName(column = "D2")
    @CsvBindByPosition(position = 29)
    public Integer D2;
    @CsvBindByName(column = "D3")
    @CsvBindByPosition(position = 30)
    public Integer D3;
    @CsvBindByName(column = "D4")
    @CsvBindByPosition(position = 31)
    public Integer D4;
    @CsvBindByName(column = "D5")
    @CsvBindByPosition(position = 32)
    public Integer D5;
    @CsvBindByName(column = "D6")
    @CsvBindByPosition(position = 33)
    public Integer D6;
    @CsvBindByName(column = "D7")
    @CsvBindByPosition(position = 34)
    public Integer D7;
    @CsvBindByName(column = "D8")
    @CsvBindByPosition(position = 35)
    public Integer D8;
}
