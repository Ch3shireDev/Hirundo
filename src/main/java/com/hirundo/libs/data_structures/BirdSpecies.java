package com.hirundo.libs.data_structures;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import java.time.LocalDateTime;

public record BirdSpecies (String speciesCode, String speciesNameEng, String speciesNameLat){

    public static class CsvReturningBirdsData {
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
        @CsvBindByPosition(position = 6)
        public String Sex;
        @CsvBindByName(column = "Age")
        @CsvBindByPosition(position = 7)
        public String Age;
        @CsvBindByName(column = "Weight")
        @CsvBindByPosition(position = 8)
        public Double Weight;
        @CsvBindByName(column = "Fat")
        @CsvBindByPosition(position = 9)
        public Double Fat;
        @CsvBindByName(column = "Wing")
        @CsvBindByPosition(position = 10)
        public Double Wing;
        @CsvBindByName(column = "Tail")
        @CsvBindByPosition(position = 11)
        public Double Tail;
        @CsvBindByName(column = "D2")
        @CsvBindByPosition(position = 12)
        public Double D2;
        @CsvBindByName(column = "D3")
        @CsvBindByPosition(position = 13)
        public Double D3;
        @CsvBindByName(column = "D4")
        @CsvBindByPosition(position = 14)
        public Double D4;
        @CsvBindByName(column = "D5")
        @CsvBindByPosition(position = 6)
        public Double D5;
        @CsvBindByName(column = "Sex")
        @CsvBindByPosition(position = 6)
        public Double D6;
        @CsvBindByName(column = "Sex")
        @CsvBindByPosition(position = 6)
        public Double D7;
        @CsvBindByName(column = "Sex")
        @CsvBindByPosition(position = 6)
        public Double D8;

        public static CsvReturningBirdsData from(ReturningBirdsData data, DbBirdRecord record) {
            var result = new CsvReturningBirdsData();
            result.RingNumber = data.RingNumber;
            result.Species = data.Species;
            result.FirstDateSeen = data.FirstDateSeen;
            result.LastDateSeen = data.LastDateSeen;
            result.FirstSeasonSeen = data.FirstSeasonSeen.toString();
            result.LastSeasonSeen = data.LastSeasonSeen.toString();
            result.RecordDate = record.getDate();
            result.Age = data.Age;
            result.Weight = data.Weight;
            result.Fat = data.Fat;
            result.Wing = data.Wing;
            result.Tail = data.Tail;
            result.Sex = data.Sex;
            result.D2 = data.D2;
            result.D3 = data.D3;
            result.D4 = data.D4;
            result.D5 = data.D5;
            result.D6 = data.D6;
            result.D7 = data.D7;
            result.D8 = data.D8;
            return result;
        }

    }
}
