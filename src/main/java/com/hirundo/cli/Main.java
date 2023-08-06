package com.hirundo.cli;

import com.hirundo.libs.data_structures.BirdSpecies;
import com.hirundo.libs.services.*;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {

        try {

            var loader = new BirdRecordDataLoaderBuilder()
                    .withOldTableName("Tab_Ring_Podab")
                    .withNewTableName("AB 2017_18_19_20_21S")
                    .withFilename("C:\\Users\\cheshire\\Documents\\GitHub\\AkcjaBaltyckaDB\\Ring_00_PODAB.mdb")
                    .build();

            var joinedData = loader.loadData();

            var finder = new ReturningBirdsSummarizer();

            var returningBirds = finder.getSummary(joinedData);

            var mapper = new ReturningBirdsDataCsvRecordMapper();
            var list = mapper.getCsvReturningBirdsData(returningBirds);

            var csvWriter = new CsvSerializer<>(BirdSpecies.CsvReturningBirdsData.class);

            var result = csvWriter.serializeToCsv(list);

            var file = new File("result.csv");

            try(var writer = new java.io.FileWriter(file, StandardCharsets.UTF_8)){
                writer.write(result);
            }
            catch (Exception e){
                System.out.println("exception = " + e);
            }
        } catch (Exception e) {
            System.out.println("exception = " + e);
        }
    }

}

