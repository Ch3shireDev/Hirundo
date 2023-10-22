package com.hirundo.cli;

import com.hirundo.libs.data_structures.CsvReturningBirdsData;
import com.hirundo.libs.loaders.BirdRecordDataLoaderBuilder;
import com.hirundo.libs.mappers.ReturningBirdsDataCsvRecordMapper;
import com.hirundo.libs.serializers.CsvSerializer;
import com.hirundo.libs.services.ReturningBirdsSummarizer;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {

        try {
            ReturningBirdsSummarizer finder = new ReturningBirdsSummarizer();
            ReturningBirdsDataCsvRecordMapper mapper = new ReturningBirdsDataCsvRecordMapper();
            CsvSerializer<CsvReturningBirdsData> csvWriter = new CsvSerializer<>(CsvReturningBirdsData.class);

            var loader = new BirdRecordDataLoaderBuilder()
                    .withOldTableName("Tab_Ring_Podab")
                    .withNewTableName("AB 2017_18_19_20_21S")
                    .withFilename("D:\\Ring_00_PODAB.mdb")
                    .build();

            var joinedData = loader.loadData();

            var returningBirds = finder.getSummary(joinedData);
            var list = mapper.getCsvReturningBirdsData(returningBirds);

            var result = csvWriter.serializeToCsv(list);

            var file = new File("result.csv");
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

