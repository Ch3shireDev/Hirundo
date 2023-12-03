package com.hirundo.cli;

import com.hirundo.libs.data_structures.CsvReturningBirdsData;
import com.hirundo.libs.data_structures.ReturningBirdsData;
import com.hirundo.libs.loaders.BirdRecordDataLoaderBuilder;
import com.hirundo.libs.mappers.ReturningBirdsDataCsvRecordMapper;
import com.hirundo.libs.serializers.CsvSerializer;
import com.hirundo.libs.services.ReturningBirdsSummarizer;
import com.hirundo.libs.services.ReturningBirdsSummarizerParameters;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        var oldTableName = "Tab_Ring_Podab";
        var newTableName = "AB 2017_18_19_20_21S";
        var fileName = "D:\\Ring_00_PODAB.mdb";
        var outputCsvFileName = "result-with-time-range.csv";

        try {
            ReturningBirdsSummarizer summarizer = new ReturningBirdsSummarizer();
            ReturningBirdsDataCsvRecordMapper mapper = new ReturningBirdsDataCsvRecordMapper();
            CsvSerializer<CsvReturningBirdsData> csvWriter = new CsvSerializer<>(CsvReturningBirdsData.class);

            var loader = new BirdRecordDataLoaderBuilder()
                    .withOldTableName(oldTableName)
                    .withNewTableName(newTableName)
                    .withFilename(fileName)
                    .build();

            var joinedData = loader.loadData();

            var parameters = new ReturningBirdsSummarizerParameters();

            parameters.useDateRange = true;
            parameters.dateRangeStart = java.time.LocalDate.of(2017, 9,1);
            parameters.dateRangeEnd = java.time.LocalDate.of(2017, 9, 30);

            List<ReturningBirdsData> returningBirds = summarizer.getSummary(joinedData, parameters);

            ArrayList<CsvReturningBirdsData> list = mapper.getCsvReturningBirdsData(returningBirds);
            var result = csvWriter.serializeToCsv(list);

            var file = new File(outputCsvFileName);
            try (var writer = new java.io.FileWriter(file, StandardCharsets.UTF_8)) {
                writer.write(result);
            } catch (Exception e) {
                System.out.println("exception = " + e);
            }
        } catch (Exception e) {
            System.out.println("exception = " + e);
        }
    }
}

