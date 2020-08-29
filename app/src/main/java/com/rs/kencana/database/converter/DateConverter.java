package com.rs.kencana.database.converter;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Date longToDate(long value) {
        if(value > 0) {
            Date date = new Date(value);
            return date;
        }
        return null;
    }

    @TypeConverter
    public static long DateToLong(Date value) {
        if(value != null) {
            return value.getTime();
        }
        return -1;
    }
}
