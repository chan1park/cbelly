package com.healthy.cbelly.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;

/**
 * Created by chan1park on 2017-09-10.
 *
 * Display Util
 */

public class DisplayUtil {

    /**
     * 화면 사이즈 리턴
     * @param context
     * @return
     */
    public static Point getDisplaySize(Activity context) {
        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    /**
     * DP to Pixel
     * @param dp
     * @param context
     * @return
     */
    public static float convertDpToPixel(Context context, float dp) {
        Resources resources= context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    public static float convertPixelsToDp(Context context, float px){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }
}
