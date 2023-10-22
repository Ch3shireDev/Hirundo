package com.hirundo.cli;

import com.hirundo.libs.data_structures.CsvReturningBirdsData;
import com.hirundo.libs.data_structures.ReturningBirdsData;
import com.hirundo.libs.loaders.BirdRecordDataLoaderBuilder;
import com.hirundo.libs.mappers.ReturningBirdsDataCsvRecordMapper;
import com.hirundo.libs.serializers.CsvSerializer;
import com.hirundo.libs.services.ReturningBirdsSummarizer;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        var oldTableName = "Tab_Ring_Podab";
        var newTableName = "AB 2017_18_19_20_21S";
        var fileName = "D:\\Ring_00_PODAB.mdb";
        var outputCsvFileName = "result.csv";

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

            List<ReturningBirdsData> returningBirds = summarizer.getSummary(joinedData);

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

