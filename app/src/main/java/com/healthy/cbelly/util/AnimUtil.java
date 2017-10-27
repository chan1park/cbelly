package com.healthy.cbelly.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.healthy.cbelly.R;


/**
 * Created by chan1park on 17.05.02
 *
 * 애니메이션 유틸
 */
public class AnimUtil {

    public static void left(Activity context) {
        context.overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    public static void right(Activity context) {
        context.overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    public static void fade(Activity context) {
        context.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public static void zoomOut(Activity context) {
        context.overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
    }


    public static void hold(Activity context) {
        context.overridePendingTransition(R.anim.hold, R.anim.hold);
    }

    public static void up(Activity context) {
        context.overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
    }

    public static void down(Activity context) {
        context.overridePendingTransition(R.anim.push_down_in,
                R.anim.push_down_out);
    }

    /**
     * A helper method to build scale down animation;
     *
     * @param target
     * @param targetScaleX
     * @param targetScaleY
     * @return
     */
    public static AnimatorSet buildScaleAnimation(View target, float targetScaleX, float targetScaleY) {

        AnimatorSet scaleDown = new AnimatorSet();
        scaleDown.playTogether(
                ObjectAnimator.ofFloat(target, "scaleX", targetScaleX),
                ObjectAnimator.ofFloat(target, "scaleY", targetScaleY)
        );
//        scaleDown.setInterpolator(AnimationUtils.loadInterpolator(activity,
//                android.R.anim.decelerate_interpolator));
//        scaleDown.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleDown.setInterpolator(new DecelerateInterpolator());
        scaleDown.setDuration(0);
        return scaleDown;
    }

    /**
     * A helper method to build scale up animation;
     *
     * @param target
     * @param targetScaleX
     * @param targetScaleY
     * @return
     */
    private AnimatorSet buildScaleUpAnimation(View target, float targetScaleX, float targetScaleY) {

        AnimatorSet scaleUp = new AnimatorSet();
        scaleUp.playTogether(
                ObjectAnimator.ofFloat(target, "scaleX", targetScaleX),
                ObjectAnimator.ofFloat(target, "scaleY", targetScaleY)
        );

        scaleUp.setDuration(250);
        return scaleUp;
    }
}
