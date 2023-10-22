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
            var csvData = map(returningBird);
            list.add(csvData);
        }
        return list;
    }

    public CsvReturningBirdsData map(ReturningBirdsData data) {
        var result = new CsvReturningBirdsData();

        result.RingNumber = data.RingNumber;
        result.Species = data.Species;
        result.FirstDateSeen = data.FirstDateSeen;
        result.LastDateSeen = data.LastDateSeen;
        result.FirstSeasonSeen = getSeasonStr(data.FirstSeasonSeen);
        result.LastSeasonSeen = getSeasonStr(data.LastSeasonSeen);
        result.BeforeMigrationCatchDate = data.BeforeMigrationCatchDate;
        result.AfterMigrationCatchDate = data.AfterMigrationCatchDate;
        result.Age = getAge(data.BirdAge);

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

        if (BirdSex.Male == data.Sex) result.Sex = "M";
        if (BirdSex.Female == data.Sex) result.Sex = "F";
        if (BirdSex.Undefined == data.Sex) result.Sex = "";

        result.D2 = data.D2;
        result.D3 = data.D3;
        result.D4 = data.D4;
        result.D5 = data.D5;
        result.D6 = data.D6;
        result.D7 = data.D7;
        result.D8 = data.D8;

        return result;
    }

    private String getAge(BirdAge birdAge) {
        if(birdAge == null) return "";

        return switch (birdAge) {
            case Infantile -> "I";
            case Juvenile -> "J";
            case Adult -> "A";
            case Other -> "O";
            default -> "";
        };
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
