package com.hirundo.cli;

import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.services.AccessNewDbBirdRecordDataLoader;
import com.hirundo.libs.services.AccessOldDbBirdRecordDataLoader;
import com.hirundo.libs.services.BirdDataLoaderAdapter;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        var dbPath = "C:\\Users\\cheshire\\Documents\\GitHub\\AkcjaBaltyckaDB\\Ring_00_PODAB.mdb";

        try {
            var oldBirdDataLoader = new AccessOldDbBirdRecordDataLoader(dbPath, "Tab_Ring_Podab");
            var oldAdapter = new BirdDataLoaderAdapter(oldBirdDataLoader);
            var oldData = oldAdapter.loadData();

            var newBirdDataLoader = new AccessNewDbBirdRecordDataLoader(dbPath, "AB 2017_18_19_20_21S");
            var newAdapter = new BirdDataLoaderAdapter(newBirdDataLoader);
            var newData = newAdapter.loadData();

            System.out.println("oldData.size() = " + oldData.size());
            System.out.println("newData.size() = " + newData.size());

            List<DbBirdRecord> joinedData = new ArrayList<>();
            joinedData.addAll(oldData);
            joinedData.addAll(newData);

            joinedData = joinedData.stream().filter(x -> null != x.getSpecies() && !x.getSpecies().isBlank()).toList();

            System.out.println("joinedData.size() = " + joinedData.size());

            var speciesSet = joinedData.stream().map(DbBirdRecord::getSpecies).distinct().sorted().toList();

            System.out.println("count of species = " + speciesSet.size());

            for (var species : speciesSet) {
                var speciesData = joinedData.stream().filter(x -> x.getSpecies().equals(species)).toList();
                System.out.println(species + ", count = " + speciesData.size());
            }

        } catch (Exception e) {
            System.out.println("exception = " + e);
        }
    }
}
