package com.hirundo.cli;

import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.services.BirdRecordDataLoaderBuilder;

import java.util.Objects;

public class Main {

    public static void main(String[] args) throws Exception {

        try {

            var loader = new BirdRecordDataLoaderBuilder()
                    .withOldTableName("Tab_Ring_Podab")
                    .withNewTableName("AB 2017_18_19_20_21S")
                    .withFilename("C:\\Users\\cheshire\\Documents\\GitHub\\AkcjaBaltyckaDB\\Ring_00_PODAB.mdb")
                    .build();

            var joinedData = loader.loadData();

            var seasons = joinedData
                    .stream()
                    .map(DbBirdRecord::getSeason)
                    .filter(Objects::nonNull)
                    .distinct()
                    .sorted()
                    .toArray();

            for (var season : seasons) {
                System.out.println(season);
            }

        } catch (Exception e) {
            System.out.println("exception = " + e);
        }
    }
}
