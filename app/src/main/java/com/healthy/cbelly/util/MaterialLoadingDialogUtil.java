package com.healthy.cbelly.util;

import android.app.Activity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.healthy.cbelly.R;

/**
 * Created by chan1park on 2017. 6. 13..
 * <p>
 * 머테리얼 디자인 로딩 다이얼로그
 */

public class MaterialLoadingDialogUtil {

    private static final String TAG = MaterialLoadingDialogUtil.class.getName();

    private MaterialDialog mMaterialDialog;

    /**
     * 기본 로딩 다이얼로그
     */
    public void show(Activity activity) {
        try {
            if (!activity.isFinishing()) {
                if (null == mMaterialDialog) {
                    mMaterialDialog = new MaterialDialog.Builder(activity)
                            .content(R.string.progress_dlg_meg)
                            .progress(true, 0)
                            .cancelable(true)
                            .build();
                    mMaterialDialog.show();
                } else {
                    mMaterialDialog.show();
                }
            }
        } catch (Exception e) {
            LogUtil.e(TAG, e);
        }
    }

    /**
     * 로딩 다이얼로그 메시지 설정 Int
     *
     * @param resId
     */
    public void show(Activity activity, int resId) {
        try {
            if (!activity.isFinishing()) {
                mMaterialDialog = new MaterialDialog.Builder(activity)
                        .content(resId)
                        .progress(true, 0)
                        .cancelable(true)
                        .build();
                mMaterialDialog.show();
            } else {
                dismiss();
                mMaterialDialog.show();
            }
        } catch (Exception e) {
            LogUtil.e(TAG, e);
        }
    }

    /**
     * 로딩 다이얼로그 메시지 설정 String
     *
     * @param msg
     */
    public void show(Activity activity, String msg) {
        try {
            if (!activity.isFinishing()) {
                mMaterialDialog = new MaterialDialog.Builder(activity)
                        .content(msg)
                        .progress(true, 0)
                        .cancelable(true)
                        .build();
                mMaterialDialog.show();
            } else {
                dismiss();
                mMaterialDialog.show();
            }
        } catch (Exception e) {
            LogUtil.e(TAG, e);
        }
    }

    /**
     * 다이얼로그 종료
     */
    public void dismiss() {
        if (null != mMaterialDialog) {
            try {
                if (mMaterialDialog.isShowing()) {
                    mMaterialDialog.dismiss();
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                mMaterialDialog.cancel();
            }
        }
    }

    /**
     * 다이얼로그 상태 반환
     *
     * @return
     */
    public boolean isShowing() {
        if (null == mMaterialDialog) return false;
        return mMaterialDialog.isShowing();
    }
}
