package com.healthy.cbelly.util;

import android.content.Context;

import com.healthy.cbelly.R;

/**
 * Create by chan1park 17.04.13
 * 슈퍼 토스트 - 토스트 메시지 사용
 * */
public class SuperToast {

    public static final int DURATION_250 = 250;
    public static final int DURATION_400 = 400;
    public static final int DURATION_500 = 500;
    public static final int DURATION_1000 = 1000;
    public static final int DURATION_1500 = 1500;
    public static final int DURATION_2000 = 2000;


    public static void show(Context act, String msg) {
//        show(act, msg, com.github.johnpersano.supertoasts.SuperToast.Duration.VERY_SHORT);
        show(act, msg, DURATION_1000);
    }

    public static void show(Context act, int res) {
//        show(act, msg, com.github.johnpersano.supertoasts.SuperToast.Duration.VERY_SHORT);
        show(act, act.getResources().getString(res), DURATION_1000);
    }

    public static void show(Context act, int res, int duration) {
//        show(act, msg, com.github.johnpersano.supertoasts.SuperToast.Duration.VERY_SHORT);
        show(act, act.getResources().getString(res), duration);
    }

    public static void show(Context act, String msg, int duration) {

        final com.github.johnpersano.supertoasts.SuperToast superToast =
                new com.github.johnpersano.supertoasts.SuperToast(act);
        superToast.setAnimations(com.github.johnpersano.supertoasts.SuperToast.Animations.FADE);
        superToast.setDuration(duration);
        //superToast.setBackground(com.github.johnpersano.supertoasts.SuperToast.Background.BLUE);

        /** 백그라운드 UI 설정 가능 (정해진게 없어서 주석처리함) */
//        superToast.setBackground(R.drawable.super_toast_background_bg);
        superToast.setTextColor(act.getResources().getColor(R.color.white));
        superToast.setTextSize(com.github.johnpersano.supertoasts.SuperToast.TextSize.SMALL);
        //        superToast.setIcon(android.R.color.transparent, com.github.johnpersano.supertoasts.SuperToast.IconPosition.LEFT);
        superToast.setText(msg);
        superToast.show();
    }
}