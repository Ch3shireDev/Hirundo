package com.hirundo.libs.services;

import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;
import com.opencsv.bean.*;

import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CsvSerializer<T> {
    private Class<? extends T> type;
    public CsvSerializer(Class<? extends T> type) {
        this.type = type;
    }
    public String serializeToCsv(List<T> birdDataList) throws Exception {

        var strategy = new ColumnPositionMappingStrategy<T>();
        strategy.setType(type);

        try  {
            Writer writer = new StringWriter();

            String[] headers = getFieldNames(type);
            strategy.setColumnMapping(headers);

            CSVWriter csvWriter = new CSVWriter(writer);
            csvWriter.writeNext(headers);

            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                    .withQuotechar(ICSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            beanToCsv.write(birdDataList);
            return writer.toString();
        } catch (Exception e) {
            throw new Exception(String.format("Error while serializing to CSV: %s", e.getMessage()));
        }
    }

    String[] getFieldNames(Class<?> cls) {
        Field[] fields = cls.getDeclaredFields(); // Get all fields from the class
        List<String> fieldNames = new ArrayList<>();

        for (Field field : fields) {
            // Check if the field has the CsvBindByName annotation
            if (field.isAnnotationPresent(CsvBindByName.class)) {
                CsvBindByName annotation = field.getAnnotation(CsvBindByName.class);
                fieldNames.add(annotation.column()); // Add the column name to the list
            }
        }

        // Convert the list to an array and return it
        return fieldNames.toArray(new String[0]);
    }

}


