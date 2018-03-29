package com.htschk.tai.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PpDateUtil {

    private static ZoneId zoneId = ZoneId.systemDefault();

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public static Date convertDateTimeFromString(String dateTimeStr) {
        if (StringUtils.isEmpty(dateTimeStr)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(dateTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
            //todo : exception handling...
            return null;
        }
    }

    public static Date convertDateFromString(String dateTimeStr) {
        if (StringUtils.isEmpty(dateTimeStr)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(dateTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
            //todo : exception handling...
            return null;
        }
    }

    public static String formatDateTime(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //todo: use thread safe mode to reduce format class
        return sdf.format(date);
    }

    public static String formatDateTimeForMonthStr(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM");
        //todo: use thread safe mode to reduce format class
        return sdf.format(date);
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //todo: use thread safe mode to reduce format class
        return sdf.format(date);
    }

    public static String formatDate1(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //todo: use thread safe mode to reduce format class
        return sdf.format(date);
    }

    public static Date transformFromLocalDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        ZonedDateTime zdt = date.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static String getCurrentDateTimeStringAsDir() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmmss");
        //todo: use thread safe mode to reduce format class
        return sdf.format(new Date());
    }

    public static String getCurrentDateTimeCommonString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //todo: use thread safe mode to reduce format class
        return sdf.format(new Date());
    }

    public static String getCurrentDateStringAsDir() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //todo: use thread safe mode to reduce format class
        return sdf.format(new Date());
    }

    public static Boolean isAfterNow(String dateTimeString) {
        LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, dateTimeFormatter);
        return dateTime.isAfter(now);
    }

    public static Boolean isValidDateTime(String dateTimeString) {
        try {
            LocalDateTime.parse(dateTimeString, dateTimeFormatter);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static long calculateDayInterval(Date recentBeginDate, Date recentEndDate) {
        LocalDate beginDate = recentBeginDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = recentEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return beginDate.until(endDate, ChronoUnit.DAYS);
    }

    public static Date calculateNewDateByInterval(Date date, long days) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Date.from(localDate.plus(days, ChronoUnit.DAYS).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static List<String> generateDateList(Date beginDate, Date endDate, Boolean ignoreWeekend) {
        List<String> dates = new ArrayList<>();
        LocalDate beginLocalDate = beginDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        while (beginLocalDate.isBefore(endLocalDate)) {
            if (ignoreWeekend) {
                if ((beginLocalDate.getDayOfWeek() != DayOfWeek.SATURDAY
                        && beginLocalDate.getDayOfWeek() != DayOfWeek.SUNDAY)) {
                    dates.add(beginLocalDate.format(formatter));
                }
            } else {
                dates.add(beginLocalDate.format(formatter));
            }
            beginLocalDate = beginLocalDate.plus(1, ChronoUnit.DAYS);
        }
        dates.add(endLocalDate.format(formatter));
        return dates;
    }
}
