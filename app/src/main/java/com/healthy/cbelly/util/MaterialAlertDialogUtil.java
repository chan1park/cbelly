package com.healthy.cbelly.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.text.TextUtils;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.healthy.cbelly.R;

/**
 * Created by chan1park on 2017. 9. 1..
 *
 * 머테리얼 디자인 알럿 다이얼로그
 */

public class MaterialAlertDialogUtil {

    public static final String TAG = MaterialAlertDialogUtil.class.getName();

    public interface OnNegativeListner {
        void onNegative(@NonNull MaterialDialog dialog, DialogAction which);
    }
    public interface OnPositiveListner {
        void onPositive(@NonNull MaterialDialog dialog, DialogAction which);
    }
    public interface OnNeutralListner {
        void onNeutral(@NonNull MaterialDialog dialog);
    }
    public interface InputListner {
        void onInput(@NonNull MaterialDialog dialog, CharSequence input);
    }

    public interface OnPositiveEtxtListner {
        void onPositive(@NonNull MaterialDialog dialog, String text1, String text2);
    }
    /**
     * 싱글 버튼 메시지 확인형 알럿
     * @param title
     * @param msg
     * @param msg
     * @param cancel
     * @param positiveTxt
     * @param onPositiveListner
     * @return
     */
    public static MaterialDialog confirmAlert(Context context, String title, String msg, boolean cancel, String positiveTxt, final OnPositiveListner onPositiveListner) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.theme(Theme.LIGHT);
        if(!TextUtils.isEmpty(title)) {
            builder.title(title);
        }
        builder.content(msg);
        builder.cancelable(cancel);
        builder.positiveText(positiveTxt);
        builder.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                if(null != onPositiveListner) onPositiveListner.onPositive(dialog, which);
            }
        });
        return builder.build();
    }

    /**
     * 텍스트 입력 확인 / 취소형 알럿
     * @param activity
     * @param title
     * @param hint
     * @param prefill
     * @param inputListner
     * @param onPositiveListner
     * @param onNegativeListner
     * @return
     */
    public static MaterialDialog inputAlert(
            Activity activity, String title, String hint, String prefill,
            final InputListner inputListner, final OnPositiveListner onPositiveListner, final OnNegativeListner onNegativeListner) {

        MaterialDialog.Builder builder = new MaterialDialog.Builder(activity);
        builder.theme(Theme.LIGHT);
        builder.title(title);

        builder.inputType(InputType.TYPE_CLASS_TEXT);
        builder.inputRange(6, 20);

        String inputHint;
        if(!TextUtils.isEmpty(hint)) inputHint = hint;
        else inputHint = "";

        builder.input(inputHint, prefill, new MaterialDialog.InputCallback() {
            @Override
            public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                if(null != inputListner) inputListner.onInput(dialog, input);
            }
        });

        builder.positiveText(activity.getResources().getString(R.string.confirm));
        builder.negativeText(activity.getResources().getString(R.string.cancel));

        builder.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                if(null != onPositiveListner) onPositiveListner.onPositive(dialog, which);
            }
        });
        builder.onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                if(null != onNegativeListner) onNegativeListner.onNegative(dialog, which);
            }
        });
        return builder.build();
    }

}
