package com.hirundo.libs.serializers;

import com.hirundo.libs.data_structures.CsvReturningBirdsData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CsvReturningBirdsDataSerializerTest {

    CsvReturningBirdsDataSerializer serializer;

    @BeforeEach
    void setUp() {
        serializer = new CsvReturningBirdsDataSerializer();
    }

    @Test
    public void NumbersOfHeadersAndDataFieldsAreEqual() throws Exception {
        var bird = new CsvReturningBirdsData();

        var result = serializer.serializeToCsv(List.of(bird));

        var settingsLine = result.split("\r\n")[0];
        var headerLine = result.split("\r\n")[1];
        var dataLine = result.split("\r\n")[2];

        var headerFields = headerLine.split(";");
        var dataFields = dataLine.split(";", -1);

        assertThat(headerFields.length, is(dataFields.length));
    }

    @Test
    public void SerializeBirdData() throws Exception {
        var bird = new CsvReturningBirdsData();
        bird.RingNumber = "LA94007";
        bird.Species = "REG.REG";
        bird.Population = 20;
        bird.RecordsCount = 3;

        bird.FirstDateSeen = LocalDateTime.of(1982, 10, 12, 0, 0);
        bird.LastDateSeen = LocalDateTime.of(1983, 4, 1, 0, 0);
        bird.FirstSeasonSeen = "Autumn";
        bird.LastSeasonSeen = "Spring";
        bird.BeforeMigrationCatchDate = LocalDateTime.of(1982, 10, 12, 0, 0);
        bird.AfterMigrationCatchDate = LocalDateTime.of(1983, 4, 1, 0, 0);
        bird.Sex = "M";
        bird.Age = "I";

        bird.Fat = 3;
        bird.FatPopulationMedian = new BigDecimal("4.123");
        bird.FatPopulationUpperQuartile = new BigDecimal("5.123");
        bird.FatPopulationLowerQuartile = new BigDecimal("6.123");

        bird.Weight = BigDecimal
                .valueOf(50.500)
                .setScale(3, RoundingMode.HALF_UP);
        bird.WeightPopulationMean = BigDecimal
                .valueOf(60.500)
                .setScale(3, RoundingMode.HALF_UP);
        bird.WeightPopulationStandardDeviation = BigDecimal
                .valueOf(10.500)
                .setScale(3, RoundingMode.HALF_UP);

        bird.Wing = BigDecimal
                .valueOf(50.500)
                .setScale(3, RoundingMode.HALF_UP);
        bird.WingPopulationMean = BigDecimal
                .valueOf(51.500)
                .setScale(3, RoundingMode.HALF_UP);
        bird.WingPopulationStandardDeviation = BigDecimal
                .valueOf(1.000)
                .setScale(3, RoundingMode.HALF_UP);

        bird.Tail = BigDecimal.valueOf(60);
        bird.TailPopulationMean = BigDecimal.valueOf(61);
        bird.TailPopulationStandardDeviation = BigDecimal.valueOf(1);

        bird.Pointedness = new BigDecimal("10.123");
        bird.PointednessPopulationMean = new BigDecimal("11.123");
        bird.PointednessPopulationStandardDeviation = new BigDecimal("12.123");

        bird.Symmetry = new BigDecimal("20.123");
        bird.SymmetryPopulationMean = new BigDecimal("21.123");
        bird.SymmetryPopulationStandardDeviation = new BigDecimal("22.123");

        bird.D2 = 1;
        bird.D3 = 2;
        bird.D4 = 3;
        bird.D5 = 4;
        bird.D6 = 5;
        bird.D7 = 6;
        bird.D8 = 7;

        var result = serializer.serializeToCsv(List.of(bird));

        var settingsLine = result.split("\r\n")[0];
        var headerLine = result.split("\r\n")[1];
        var dataLine = result.split("\r\n")[2];

        HashMap<String, String> valuesDictionary = getHeaderValueDictionary(headerLine, dataLine);

        assertThat(settingsLine, containsString("SEP=;"));

        assertThat(headerLine, containsString("RingNumber"));
        assertThat(valuesDictionary.get("RingNumber"), is("LA94007"));

        assertThat(headerLine, containsString("Species"));
        assertThat(valuesDictionary.get("Species"), is("REG.REG"));

        assertThat(headerLine, containsString("Population"));
        assertThat(valuesDictionary.get("Population"), is("20"));

        assertThat(headerLine, containsString("RecordsCount"));
        assertThat(valuesDictionary.get("RecordsCount"), is("3"));

        assertThat(headerLine, containsString("FirstDateSeen"));
        assertThat(valuesDictionary.get("FirstDateSeen"), is("1982-10-12"));

        assertThat(headerLine, containsString("LastDateSeen"));
        assertThat(valuesDictionary.get("LastDateSeen"), is("1983-04-01"));

        assertThat(headerLine, containsString("FirstSeasonSeen"));
        assertThat(valuesDictionary.get("FirstSeasonSeen"), is("Autumn"));

        assertThat(headerLine, containsString("LastSeasonSeen"));
        assertThat(valuesDictionary.get("LastSeasonSeen"), is("Spring"));

        assertThat(headerLine, containsString("BeforeMigrationCatchDate"));
        assertThat(valuesDictionary.get("BeforeMigrationCatchDate"), is("1982-10-12"));

        assertThat(headerLine, containsString("AfterMigrationCatchDate"));
        assertThat(valuesDictionary.get("AfterMigrationCatchDate"), is("1983-04-01"));

        assertThat(headerLine, containsString("Sex"));
        assertThat(valuesDictionary.get("Sex"), is("M"));

        assertThat(headerLine, containsString("Age"));
        assertThat(valuesDictionary.get("Age"), is("I"));

        assertThat(headerLine, containsString("Fat"));
        assertThat(valuesDictionary.get("Fat"), is("3"));
        assertThat(headerLine, containsString("FatPopulationMedian"));
        assertThat(valuesDictionary.get("FatPopulationMedian"), is("4.123"));
        assertThat(headerLine, containsString("FatPopulationUpperQuartile"));
        assertThat(valuesDictionary.get("FatPopulationUpperQuartile"), is("5.123"));
        assertThat(headerLine, containsString("FatPopulationLowerQuartile"));
        assertThat(valuesDictionary.get("FatPopulationLowerQuartile"), is("6.123"));

        assertThat(headerLine, containsString("Weight"));
        assertThat(valuesDictionary.get("Weight"), is("50.500"));
        assertThat(headerLine, containsString("WeightPopulationMean"));
        assertThat(valuesDictionary.get("WeightPopulationMean"), is("60.500"));
        assertThat(headerLine, containsString("WeightPopulationStandardDeviation"));
        assertThat(valuesDictionary.get("WeightPopulationStandardDeviation"), is("10.500"));

        assertThat(headerLine, containsString("Wing"));
        assertThat(valuesDictionary.get("Wing"), is("50.500"));
        assertThat(headerLine, containsString("WingPopulationMean"));
        assertThat(valuesDictionary.get("WingPopulationMean"), is("51.500"));
        assertThat(headerLine, containsString("WingPopulationStandardDeviation"));
        assertThat(valuesDictionary.get("WingPopulationStandardDeviation"), is("1.000"));

        assertThat(headerLine, containsString("Tail"));
        assertThat(valuesDictionary.get("Tail"), is("60"));
        assertThat(headerLine, containsString("TailPopulationMean"));
        assertThat(valuesDictionary.get("TailPopulationMean"), is("61"));
        assertThat(headerLine, containsString("TailPopulationStandardDeviation"));
        assertThat(valuesDictionary.get("TailPopulationStandardDeviation"), is("1"));

        assertThat(headerLine, containsString("Pointedness"));
        assertThat(valuesDictionary.get("Pointedness"), is("10.123"));
        assertThat(headerLine, containsString("PointednessPopulationMean"));
        assertThat(valuesDictionary.get("PointednessPopulationMean"), is("11.123"));
        assertThat(headerLine, containsString("PointednessPopulationStandardDeviation"));
        assertThat(valuesDictionary.get("PointednessPopulationStandardDeviation"), is("12.123"));

        assertThat(headerLine, containsString("Symmetry"));
        assertThat(valuesDictionary.get("Symmetry"), is("20.123"));
        assertThat(headerLine, containsString("SymmetryPopulationMean"));
        assertThat(valuesDictionary.get("SymmetryPopulationMean"), is("21.123"));
        assertThat(headerLine, containsString("SymmetryPopulationStandardDeviation"));
        assertThat(valuesDictionary.get("SymmetryPopulationStandardDeviation"), is("22.123"));

        assertThat(headerLine, containsString("D2"));
        assertThat(valuesDictionary.get("D2"), is("1"));

        assertThat(headerLine, containsString("D3"));
        assertThat(valuesDictionary.get("D3"), is("2"));

        assertThat(headerLine, containsString("D4"));
        assertThat(valuesDictionary.get("D4"), is("3"));

        assertThat(headerLine, containsString("D5"));
        assertThat(valuesDictionary.get("D5"), is("4"));

        assertThat(headerLine, containsString("D6"));
        assertThat(valuesDictionary.get("D6"), is("5"));

        assertThat(headerLine, containsString("D7"));
        assertThat(valuesDictionary.get("D7"), is("6"));

        assertThat(headerLine, containsString("D8"));
        assertThat(valuesDictionary.get("D8"), is("7"));
    }

    private HashMap<String, String> getHeaderValueDictionary(String headerLine, String dataLine) {
        var headerFields = headerLine.split(";");
        var dataFields = dataLine.split(";");

        var valuesDictionary = new HashMap<String, String>();
        for (int i = 0; i < headerFields.length; i++) {
            valuesDictionary.put(headerFields[i], dataFields[i]);
        }
        return valuesDictionary;
    }
}
