package com.healthy.cbelly.util;

import android.annotation.SuppressLint;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class UtcDateFormatter extends SimpleDateFormat
{
  private static final  String    TIME_ZONE_STRING  = "UTC";
  private static final  TimeZone  TIME_ZONE_UTC     = TimeZone.getTimeZone("UTC");
  private static final  long      serialVersionUID  = 1L;
  //[*]-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------[*]
  @SuppressLint({"SimpleDateFormat"})
  public UtcDateFormatter(String InitStr)
  {
    super(InitStr);
    super.setTimeZone(TIME_ZONE_UTC);
  }
  
  @SuppressLint({"SimpleDateFormat"})
  public UtcDateFormatter(String paramString, DateFormatSymbols paramDateFormatSymbols)
  {
    super(paramString, paramDateFormatSymbols);
    super.setTimeZone(TIME_ZONE_UTC);
  }
  
  public UtcDateFormatter(String paramString, Locale paramLocale)
  {
    super(paramString, paramLocale);
    super.setTimeZone(TIME_ZONE_UTC);
  }
  
  public void setTimeZone(TimeZone timezone)
  {
    throw new UnsupportedOperationException("This SimpleDateFormat can only be in UTC");
  }
}
