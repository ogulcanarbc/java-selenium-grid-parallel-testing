package helper.date;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateAndTimeHelper {
    private static final String dd_MM_YYYY = "dd-MM-yyyy";
    private static final String HH_mm_ss = "HH:mm:ss";
    private static final String dd_MM_YYYY_HH_mm_ss = dd_MM_YYYY + " " + HH_mm_ss;

    private DateAndTimeHelper() {
    }

    public static Date getNowDate() {
        return getCalendar().getTime();
    }


    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    public static DateFormat getDateFormat(String format) {
        return new SimpleDateFormat(format);
    }

    public static String formatDate(Date date, String format) {
        return getDateFormat(format).format(date);
    }


    public static String getNowDateAsString() {
        return formatDate(getNowDate(), dd_MM_YYYY_HH_mm_ss);
    }

    public static String getNowDateDayMonthYearFormatAsString() {
        return formatDate(getNowDate(), dd_MM_YYYY);
    }

    public static String getNowDateDHoursMinuteSecondFormatAsString() {
        return formatDate(getNowDate(), HH_mm_ss);
    }


}
