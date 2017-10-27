package com.healthy.cbelly.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by chan1park on 2017.06.22
 * <p/>
 * 날짜 포맷
 */
public class DateFormatUtil {

    private static final String TAG = DateFormatUtil.class.getName();

    public static final String FORMAT_DATE_YYYY_YEAR_MM_MONTH_DD_DAY = "yyyy년 MM월 dd일";
    public static final String FORMAT_DATE_MM_MONTH_DD_DAY = "M월 d일"; //ex ) 2015년 04월 11일
    public static final String FORMAT_TIME_A_HH_HOUR_MM_MINUTE = "a hh시 mm분";  // ex) 오전 01시 10분
    public static final String FORMAT_TIME_HH24MI = "HHmm";  //ex ) 1630
    public static final String FORMAT_TIME_YYYYMMDD = "yyyyMMdd"; //ex) 20150518
    public static final String FORMAT_TIME_YYYYMMDDHHMMSS = "yyyyMMddhhmmss"; //ex) 20150518

    public static final String FORMAT_TIME_YYYY_MM_DD = "yyyy-MM-dd"; //ex) 20150518
    public static final String FORMAT_TIME_HH24MISS = "HHmmss";  //ex ) 163018
    public static final String FORMAT_TIME_PARENTHESIS_YYYY_MM_DD = "(yyyy.MM.dd)";  //ex ) (2016.09.21)
    public static final String FORMAT_TIME_YYYY_DOT_MM_DOT_DD = "yyyy.MM.dd";  //ex ) (2016.09.21)
    public static final String FORMAT_TIME_YYYY_DOT_MM_DOT_DD_WEEK = "yyyy.MM.dd(E)";  //ex ) (2016.09.21)
    public static final String FORMAT_TIME_YYYY_SS_MM_SS_DD_WEEK = "yyyy/MM/dd";  //ex ) (2016/09/21)

    /**
     * Default Time 포맷으로 리턴
     * ex) 오전 01시 10분
     *
     * @param hourOfDay 시
     * @param minute    분
     */
    public static String getTimeFormat(int hourOfDay, int minute) {
        return getTimeFormat(FORMAT_TIME_A_HH_HOUR_MM_MINUTE, hourOfDay, minute);
    }

    /**
     * 셋팅된 Time 포맷팅으로 리턴
     *
     * @param format    포맷형식
     * @param hourOfDay 시간
     * @param minute    분
     */
    public static String getTimeFormat(String format, int hourOfDay, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, minute);
        return format(format, cal);
    }

    /**
     * Default Date 포맷 리턴
     * ex) 2015년 04월 11일
     *
     * @param month      월
     * @param dayOfMonth 일
     */
    public static String getDateFormat(int year, int month, int dayOfMonth) {
        return getDateFormat(FORMAT_DATE_YYYY_YEAR_MM_MONTH_DD_DAY, year, month, dayOfMonth);
    }

    /**
     * 셋팅된 Date 포맷팅으로 리턴
     *
     * @param format     변경할 포맷
     * @param month      월
     * @param dayOfMonth 일
     */
    public static String getDateFormat(String format, int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        return format(format, cal);
    }

    /**
     * 현재 Date를 Default 포맷 값으로 리턴
     * ex) 2015년 04월 11일
     */
    public static final String getCurrentDateFormat() {
        return getCurrentDateTimeFormat(FORMAT_DATE_YYYY_YEAR_MM_MONTH_DD_DAY);
    }

    /**
     * 현재 Time을 Default 포맷 값으로 리턴
     * ex) 오전 01시 10분
     */
    public static final String getCurrentTimeFormat() {
        return getCurrentDateTimeFormat(FORMAT_TIME_A_HH_HOUR_MM_MINUTE);
    }

    /**
     * 현재 Date를 셋팅된 format 값으로 리턴
     *
     * @param format 변경할 포맷
     */
    public static final String getCurrentDateTimeFormat(String format) {
        return format(format, Calendar.getInstance());
    }

    /**
     * 증간된 날짜를 셋팅된 format으로 리턴
     * <p/>
     * 파라미터에 증가할 숫자를 넣는다. 변경하지 않을 숫자에는 0을 넣으면 된다.
     * ex) 3일 후면 day parameter 에 3 을 넣고 3일 전이라면 day parameter 에 -3 을 넣는다.
     *
     * @param format     변경할 포맷
     * @param year       년
     * @param month      월
     * @param dayOfMonth 일
     * @param hour       시
     * @param minute     분
     */
    public static final String getAfterDateTimeFormat(String format, int year, int month,
                                                      int dayOfMonth, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, year);
        cal.add(Calendar.MONTH, month);
        cal.add(Calendar.DAY_OF_MONTH, dayOfMonth);
        cal.add(Calendar.HOUR, hour);
        cal.add(Calendar.MINUTE, minute);

        return format(format, cal);
    }

    /**
     * Calndar 를 원하는 날짜 포맷으로 리턴
     *
     * @param format 포맷형식
     * @param cal    변경할 Calendar
     */
    public static String format(String format, Calendar cal) {
        SimpleDateFormat formater = new SimpleDateFormat(format);
        return formater.format(cal.getTime());
    }

    /**
     * String 형태의 날짜를 Calendar Instance로 변환하여 리턴한다.
     *
     * @param format Date 형식 포맷
     * @param date   날짜 string 값
     */
    public static Calendar getCalendar(String format, String date) {
        Calendar cal = Calendar.getInstance();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            cal.setTime(formatter.parse(date));
        } catch (Exception e) {
            LogUtil.d(TAG, e.toString());
        }
        return cal;
    }

    /**
     * 특정일 이전 / 이후  날짜 구하기
     *
     * @param year     년도
     * @param month    월
     * @param day      일
     * @param minusDay 마이너스 데이
     * @return
     */
    public static String getDayAgoDate(int year, int month, int day, int minusDay) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        cal.add(Calendar.DATE, minusDay);
        java.util.Date weekago = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM 월 dd 일",
                Locale.getDefault());
        return formatter.format(weekago);
    }

    /**
     * 특정일 이전 / 이후  날짜 구하기
     *
     * @param cal      calender
     * @param minusDay 마이너스 데이
     * @return
     */
    public static String getDayAgoDate(Calendar cal, int minusDay) {
        cal.add(Calendar.DATE, minusDay);
        java.util.Date weekago = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM 월 dd 일",
                Locale.getDefault());
        return formatter.format(weekago);
    }

    /**
     * 특정일 이전 / 이후  날짜 구하기
     *
     * @param cal      calender
     * @return
     */
    public static String getFormatDate(Calendar cal) {
        cal.add(Calendar.DATE, 0);
        java.util.Date weekago = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("MM 월 dd 일",
                Locale.getDefault());
        return formatter.format(weekago);
    }

    /**
     * 오늘부터 한달 후 날짜 구하기
     *
     * @return
     */
    public static String getOneMonthGo(String format) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.getTime();
        return format(format, cal);
    }


    /**
     * 날짜 캘린더 데이터 생성
     *
     * @param date 날짜 데이터
     * @return
     */
    public static Calendar getCalendar(String date) {
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(date.substring(0, 4)); // 년
        int month = Integer.parseInt(date.substring(4, 6)); // 월
        int day = Integer.parseInt(date.substring(6, 8)); // 일
        cal.set(year, month - 1, day); // 월은 0 부터 계산 하므로 -1
        return cal;
    }
}
