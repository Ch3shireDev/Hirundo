package com.hirundo.app.services;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;

public class CsvFileWriter implements ICsvFileWriter {
    public void writeToFile(String filepath, String data) throws Exception {
        if (null == filepath || filepath.isEmpty()) {
            throw new Exception("Nie można zapisać pustych danych do pliku");
        }
        if (null == data || data.isEmpty()) {
            throw new Exception("Nie można zapisać pustych danych do pliku");
        }
        try {
            var file = new File(filepath);
            var writer = new FileWriter(file, StandardCharsets.UTF_8);
            writer.write(data);
            writer.close();
        } catch (Exception e) {
            throw new Exception(String.format("Błąd podczas zapisu do pliku: %s. Informacja o błędzie: %s",
                                              filepath,
                                              e.getMessage()));
        }
    }
}
