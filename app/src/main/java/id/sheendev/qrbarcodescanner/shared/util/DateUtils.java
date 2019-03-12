package id.sheendev.qrbarcodescanner.shared.util;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Rizki Maulana on 1/4/18.
 * email : rizmaulana@live.com
 * Mobile App Developer
 */

public class DateUtils {

    public static int DATE       = 0;
    public static int MONTH      = 1;
    public static int YEAR       = 2;
    public static int ALL        = 3;
    public static int SQL_FORMAT = 4;


    public static String getReadableTimeFromSql(String dateSql, int tipe) {
        try {
            DateFormat format         = new SimpleDateFormat("yyyy-MM-dd");
            Date       date           = format.parse(dateSql);
            DateFormat writableFormat = new SimpleDateFormat("dd MMMM yyyy");
            String[]   dates          = writableFormat.format(date).split(" ");
            switch (tipe) {
                case 0:
                    return dates[0];
                case 1:
                    return dates[1];
                case 2:
                    return dates[2];
                default:
                    return writableFormat.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getRelativeTime(Context context, String dateSql) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date             date   = format.parse(dateSql);
            return android.text.format.DateUtils.getRelativeTimeSpanString(context, date.getTime()).toString();

        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String timestampToReadable(String timestamp, String formatDate) {
        try {
            DateFormat format         = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date       date           = format.parse(timestamp);
            DateFormat writableFormat = new SimpleDateFormat(formatDate);
            String[]   dates          = writableFormat.format(date).split(" ");
            return writableFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getSqlTimeFromCalender(Calendar calendar, int tipe) {
        Date       date           = calendar.getTime();
        DateFormat writableFormat = new SimpleDateFormat("yyyy-MM-dd");
        String[]   dates          = writableFormat.format(date).split(" ");
        switch (tipe) {
            case 0:
                return dates[2];
            case 1:
                return dates[1];
            case 2:
                return dates[0];
            default:
                return writableFormat.format(date);
        }
    }


    public static String getTodayDate(String formatDate) {
        DateFormat format = new SimpleDateFormat(formatDate);
        Date       date   = new Date();
        return format.format(date);
    }

    public static String calenderApartToSql(int day, int month, int year) {
        return year + "-" + (month + 1) + "-" + day;
    }


    public static String getTimeStamp() {
        long unixTime = System.currentTimeMillis() / 1000L;
        return String.valueOf(unixTime);
    }

    public static String getStringTimeStamp() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm");

        return dateFormat.format(now);
    }


}


