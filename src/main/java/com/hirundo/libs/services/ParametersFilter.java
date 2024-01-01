package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.BirdSex;
import com.hirundo.libs.data_structures.DbBirdRecord;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

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
        var date = record.date;
        var dateStart = getDateForSameYear(parameters.dateRangeStart, date.getYear());
        var dateEnd = getDateForSameYear(parameters.dateRangeEnd, date.getYear());
        if (date.isEqual(dateStart) || date.isEqual(dateEnd)) return true;
        return date.isAfter(dateStart) && date.isBefore(dateEnd);
    }

    LocalDateTime getDateForSameYear(LocalDate date, int year){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, date.getMonthValue()-1);
        cal.set(Calendar.DAY_OF_MONTH, date.getDayOfMonth());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        return LocalDateTime.ofInstant(cal.toInstant(), cal.getTimeZone().toZoneId());
    }
}
