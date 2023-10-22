package com.hirundo.libs.data_structures;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReturningBirdsData {
    public List<DbBirdRecord> Records;
    public int Population;
    public String RingNumber;
    public String Species;
    public LocalDateTime FirstDateSeen;
    public LocalDateTime LastDateSeen;
    public LocalDateTime BeforeMigrationCatchDate;
    public LocalDateTime AfterMigrationCatchDate;
    public Season FirstSeasonSeen;
    public Season LastSeasonSeen;
    public BigDecimal Pointedness;
    public BigDecimal PointednessMean;
    public BigDecimal PointednessStandardDeviation;
    public BigDecimal Symmetry;
    public BigDecimal SymmetryMean;
    public BigDecimal SymmetryStandardDeviation;
    public BigDecimal Weight;
    public BigDecimal WeightMean;
    public BigDecimal WeightStandardDeviation;
    public Integer Fat;
    public BigDecimal FatMedian;
    public BigDecimal FatUpperQuartile;
    public BigDecimal FatLowerQuartile;
    public BigDecimal Wing;
    public BigDecimal WingMean;
    public BigDecimal WingStandardDeviation;
    public BigDecimal Tail;
    public BigDecimal TailMean;
    public BigDecimal TailStandardDeviation;
    public Integer D2;
    public Integer D3;
    public Integer D4;
    public Integer D5;
    public Integer D6;
    public Integer D7;
    public Integer D8;
    public String BirdAge;
    public BirdSex Sex;
}
