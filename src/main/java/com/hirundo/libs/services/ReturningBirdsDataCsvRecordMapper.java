package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.BirdSex;
import com.hirundo.libs.data_structures.CsvReturningBirdsData;
import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.ReturningBirdsData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ReturningBirdsDataCsvRecordMapper implements IReturningBirdsDataCsvRecordMapper {

    public CsvReturningBirdsData map(ReturningBirdsData data, DbBirdRecord record) {
        var result = new CsvReturningBirdsData();
        result.RingNumber = data.RingNumber;
        result.Species = data.Species;
        result.FirstDateSeen = data.FirstDateSeen;
        result.LastDateSeen = data.LastDateSeen;
        result.FirstSeasonSeen = data.FirstSeasonSeen.toString();
        result.LastSeasonSeen = data.LastSeasonSeen.toString();
        result.RecordDate = record.getDate();
        result.Age = record.getAge();
        result.Weight = roundDecimal(record.getWeight());
        result.Fat = record.getFat();
        result.Wing = roundDecimal(record.getWing());
        result.Tail = record.getTail();

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

    static BigDecimal roundDecimal(BigDecimal value) {
        if (null == value) return null;
        return value.setScale(3, RoundingMode.FLOOR);
    }

    @Override
    public ArrayList<CsvReturningBirdsData> getCsvReturningBirdsData(List<ReturningBirdsData> returningBirds) {
        var list = new ArrayList<CsvReturningBirdsData>();

        for (var returningBird : returningBirds) {

            for (var record : returningBird.Records) {

                var csvData = map(returningBird, record);
                list.add(csvData);
            }
        }
        return list;
    }

}
