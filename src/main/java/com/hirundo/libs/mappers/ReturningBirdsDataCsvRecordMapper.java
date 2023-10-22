package com.hirundo.libs.mappers;

import com.hirundo.libs.data_structures.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ReturningBirdsDataCsvRecordMapper implements IReturningBirdsDataCsvRecordMapper {

    @Override
    public ArrayList<CsvReturningBirdsData> getCsvReturningBirdsData(List<ReturningBirdsData> returningBirds) {
        var list = new ArrayList<CsvReturningBirdsData>();

        for (var returningBird : returningBirds) {
            var record = returningBird.Records.get(0);
            var csvData = map(returningBird, record);
            list.add(csvData);
        }
        return list;
    }

    public CsvReturningBirdsData map(ReturningBirdsData data, DbBirdRecord record) {
        var result = new CsvReturningBirdsData();
        result.RingNumber = data.RingNumber;
        result.Species = data.Species;
        result.FirstDateSeen = data.FirstDateSeen;
        result.LastDateSeen = data.LastDateSeen;
        result.FirstSeasonSeen = getSeasonStr(data.FirstSeasonSeen);
        result.LastSeasonSeen = getSeasonStr(data.LastSeasonSeen);
        result.RecordDate = record.getDate();
        result.Age = record.getAge();

        result.Weight = roundDecimal(data.Weight);
        result.WeightPopulationMean = roundDecimal(data.WeightMean);
        result.WeightPopulationStandardDeviation = roundDecimal(data.WeightStandardDeviation);

        result.Fat = data.Fat;
        result.FatPopulationMedian = data.FatMedian;
        result.FatPopulationUpperQuartile = data.FatUpperQuartile;
        result.FatPopulationLowerQuartile = data.FatLowerQuartile;

        result.Wing = roundDecimal(data.Wing);
        result.WingPopulationMean = roundDecimal(data.WingMean);
        result.WingPopulationStandardDeviation = roundDecimal(data.WingStandardDeviation);

        result.Tail = roundDecimal(data.Tail);
        result.TailPopulationMean = roundDecimal(data.TailMean);
        result.TailPopulationStandardDeviation = roundDecimal(data.TailStandardDeviation);

        result.Pointedness = roundDecimal(data.Pointedness);
        result.PointednessPopulationMean = roundDecimal(data.PointednessMean);
        result.PointednessPopulationStandardDeviation = roundDecimal(data.PointednessStandardDeviation);

        result.Symmetry = roundDecimal(data.Symmetry);
        result.SymmetryPopulationMean = roundDecimal(data.SymmetryMean);
        result.SymmetryPopulationStandardDeviation = roundDecimal(data.SymmetryStandardDeviation);

        result.Population = data.Population;

        if (BirdSex.Male == record.getSex()) result.Sex = "M";
        if (BirdSex.Female == record.getSex()) result.Sex = "F";
        if (BirdSex.Undefined == record.getSex()) result.Sex = "";

        result.D2 = record.getD2();
        result.D3 = record.getD3();
        result.D4 = record.getD4();
        result.D5 = record.getD5();
        result.D6 = record.getD6();
        result.D7 = record.getD7();
        result.D8 = record.getD8();

        return result;
    }

    private String getSeasonStr(Season season) {
        if (season != null) {
            return season.toString();
        }
        return "";
    }

    private BigDecimal roundDecimal(BigDecimal value) {
        if (null == value) return null;
        return value.setScale(3, RoundingMode.FLOOR);
    }

}
