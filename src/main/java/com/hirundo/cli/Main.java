package com.hirundo.cli;

import com.hirundo.libs.services.BirdRecordDataLoaderBuilder;
import com.hirundo.libs.services.CsvReturningBirdsData;
import com.hirundo.libs.services.CsvSerializer;
import com.hirundo.libs.services.ReturningBirdsFinder;

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

            var finder = new ReturningBirdsFinder();

            var returningBirds = finder.findReturningBirds(joinedData);

            var list = new java.util.ArrayList<CsvReturningBirdsData>();

            for (var returningBird : returningBirds) {

                for (var record : returningBird.Records) {
                    var csvData = CsvReturningBirdsData.from(returningBird, record);
                    list.add(csvData);
                }
            }

            var csvWriter = new CsvSerializer<>(CsvReturningBirdsData.class);

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
