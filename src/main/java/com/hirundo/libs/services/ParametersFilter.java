package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.BirdSex;
import com.hirundo.libs.data_structures.DbBirdRecord;

public class ParametersFilter {
    public boolean filter(DbBirdRecord record, ReturningBirdsSummarizerParameters parameters) {

        if (BirdSex.Male == parameters.sex || BirdSex.Female == parameters.sex) {
            if (record.sex != parameters.sex) return false;
        }

        return isInDateRange(record, parameters);
    }

    private boolean isInDateRange(DbBirdRecord record, ReturningBirdsSummarizerParameters parameters) {
        if (!parameters.useDateRange || null == parameters.dateRangeStart || null == parameters.dateRangeEnd) {
            return true;
        }

        if(record.date.getMonthValue() < parameters.dateRangeStart.getMonthValue()) return false;
        if(record.date.getDayOfMonth() < parameters.dateRangeStart.getDayOfMonth()) return false;
        if(record.date.getMonthValue() > parameters.dateRangeEnd.getMonthValue()) return false;
        return record.date.getDayOfMonth() <= parameters.dateRangeEnd.getDayOfMonth();
    }
}
