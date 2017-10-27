package com.healthy.cbelly.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeFormatter {

    private static final String TAG = TimeFormatter.class.getName();

    private static final String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS zzz";
    private static final SimpleDateFormat ISO_FORMATTER = new UtcDateFormatter("yyyy-MM-dd'T'HH:mm:ss.SSS zzz", Locale.US);

    //[*]-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------[*]
    public static String getIsoDateTime(long Value) {
        return getIsoDateTime(new Date(Value));
    }

    public static String getIsoDateTime(Date paramDate) {
        String time = ISO_FORMATTER.format(paramDate);
        LogUtil.d(TAG, "TimeFormatter_Send_Time === " + time);
        return time;
    }
}
