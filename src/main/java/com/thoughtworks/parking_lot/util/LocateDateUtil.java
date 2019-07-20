package com.thoughtworks.parking_lot.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class LocateDateUtil {
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String HHMMSS = "HHmmss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String YYYYMMDD_HH_MM_SS = "yyyyMMdd HH:mm:ss";
    public static final String YYYY1MM1DDHHMMSS_SSS = "yyyy/MM/dd:HH:mm:ss.SSS";
    public static final String YYYY1MM1DDHHMMSSSSSZZ = "yyyy/MM/dd:HH:mm:ss.SSS ZZ";
    public static final String YYYY1MM1DDHHMMSS = "yyyy/MM/dd HH:mm:ss";
    public static final String YYYY_MM_DDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String MM_DD = "MM-dd";
    public static final int ZERO = 0;
    public static final ZoneId zoneId = ZoneId.systemDefault();

    public static Date getCurrentDate() {
        return new Date();
    }

    public static Date getYesterday(Date date) {
        LocalDateTime localDateTime = getLocalDateTime(date).plusDays(-1);
        return convertToDate(localDateTime);
    }

    public static Date getIntervalDay(Date date, int day) {
        LocalDateTime localDateTime = getLocalDateTime(date).plusDays(day);
        return convertToDate(localDateTime);
    }

    public static Date getIntervalHours(Date date, int hours) {
        LocalDateTime localDateTime = getLocalDateTime(date).plusHours(hours);
        return convertToDate(localDateTime);
    }

    public static Date getIntervalMinutes(Date date, int minutes) {
        LocalDateTime localDateTime = getLocalDateTime(date).plusMinutes(minutes);
        return convertToDate(localDateTime);
    }

    public static Date getIntervalSeconds(Date date, int seconds) {
        LocalDateTime localDateTime = getLocalDateTime(date).plusSeconds(seconds);
        return convertToDate(localDateTime);
    }

    public static Date convertStringToDate(String date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("日期转换异常： " + date, e);
        }
    }

    public static String convertDateToString(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static String getIntervalDate(Date date, String format, int days) {
        Date expectedDate = getIntervalDay(date, days);
        return convertDateToString(expectedDate, format);
    }

    public static String getIntervalMinute(Date date, String format, int minutes) {
        Date expectedDate = getIntervalMinutes(date, minutes);
        return convertDateToString(expectedDate, format);
    }

    public static Date getStartTimeOfSpecifiedDate(Date date) {
        LocalDateTime localDateTime = getLocalDateTime(date);
        localDateTime = localDateTime.withHour(ZERO);
        localDateTime = localDateTime.withSecond(ZERO);
        localDateTime = localDateTime.withMinute(ZERO);
        localDateTime = localDateTime.withNano(ZERO);
        return convertToDate(localDateTime);
    }

    public static int getBetweenDays(Date startDate, Date endDate) {
        LocalDateTime startLocalDate = getLocalDateTime(startDate);
        LocalDateTime endLocalDate = getLocalDateTime(endDate);
        Duration duration = Duration.between(startLocalDate, endLocalDate);
        return (int) duration.toDays();
    }

    public static Date convertToDate(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(zoneId).toInstant();
        return new Date(instant.toEpochMilli());
    }

    public static LocalDateTime getLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

}
