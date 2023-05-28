package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.ReturningBirdsData;
import com.hirundo.libs.data_structures.DbBirdRecord;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

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

    public static CsvReturningBirdsData from(ReturningBirdsData data, DbBirdRecord record) {
        var result = new CsvReturningBirdsData();
        result.RingNumber = data.RingNumber;
        result.Species = data.Species;
        result.FirstDateSeen = data.FirstDateSeen;
        result.LastDateSeen = data.LastDateSeen;
        result.FirstSeasonSeen = data.FirstSeasonSeen.toString();
        result.LastSeasonSeen = data.LastSeasonSeen.toString();
        result.RecordDate = record.getDate();
        return result;
    }

}
