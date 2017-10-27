package com.healthy.cbelly.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Create by chan1park 17.04.13
 * 로그 유틸
 */
public class LogUtil {

  private static final String TAG_REGREX = "%s=";

  public static void d(String log) {
      String tag = String.format(TAG_REGREX, log);
      Log.d(tag, log);
  }

  public static void d(Class<?> cls, String log) {
      Log.d(cls.getCanonicalName(), log);
  }

  public static void d(String tag, String log) {
      Log.d(tag, log);
  }

  public static void i(String log) {
      String tag = String.format(TAG_REGREX, log);
      Log.i(tag, log);
  }

  public static void i(Class<?> cls, String log) {
      Log.i(cls.getCanonicalName(), log);
  }

  public static void i(String tag, String log) {
      Log.i(tag, log);
  }

  public static void toast(Context ctx, String log) {
      String tag = String.format(TAG_REGREX, log);
      Log.d(tag, log);

      Toast.makeText(ctx, log, Toast.LENGTH_LONG).show();
  }

  public static void e(String tag, Exception error) {
      Log.e(tag, "error", error);
  }

  public static void e(String tag, String error) {
      Log.e(tag, error);
  }

  public static void map(String tag, Map<String, String> data) {
    Iterator<String> iterator = data.keySet().iterator();
    while (iterator.hasNext()) {
      String key = (String) iterator.next();
      String value = data.get(key);
      d(tag, "key=" + key + ",value=" + value);
    }
  }

  public static void list(String tag, List<Map<String, String>> list) {
    for (int i = 0; i < list.size(); i++) {
      Map<String, String> data = list.get(i);
      map(tag, data);
    }
  }
}
