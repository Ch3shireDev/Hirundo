package com.hirundo.libs.serializers;

import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CsvSerializer<T> implements ICsvSerializer<T> {
    private Class<? extends T> type;

    public CsvSerializer(Class<? extends T> type) {
        this.type = type;
    }

    public String serializeToCsv(List<T> birdDataList) throws Exception {


        try {
            Writer writer = new StringWriter();

            String[] headers = getFieldNames(type);

            CSVWriter csvWriter = new CSVWriter(writer, ';',
                                                ICSVWriter.NO_QUOTE_CHARACTER,
                                                ICSVWriter.DEFAULT_ESCAPE_CHARACTER,
                                                ICSVWriter.RFC4180_LINE_END);

            writer.write("SEP=;\r\n");
            csvWriter.writeNext(headers);

            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                    .withQuotechar(ICSVWriter.NO_QUOTE_CHARACTER)
                    .withLineEnd(ICSVWriter.RFC4180_LINE_END)
                    .withSeparator(';')
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


