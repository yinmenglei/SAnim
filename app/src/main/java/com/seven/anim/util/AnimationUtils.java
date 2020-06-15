package com.seven.anim.util;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.TextView;


public final class AnimationUtils {

    public static void shakeAnim(EditText et) {
        TranslateAnimation mTranslateAnimation = new TranslateAnimation(-10, 10, 0, 0);
        mTranslateAnimation.setInterpolator(new CycleInterpolator(2f));
        mTranslateAnimation.setDuration(500);
        et.startAnimation(mTranslateAnimation);
    }

    public static void showView(View hidView, View showView, int inAnimation, int outAnimation) {
        showView(showView, inAnimation);
        hidView(hidView, outAnimation);
    }

    public static void showView(View view, int animation) {
        if (view != null) {
            view.setAnimation(android.view.animation.AnimationUtils.loadAnimation(view.getContext(), animation));
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void hidView(View view, int animation) {
        if (view != null) {
            view.setAnimation(android.view.animation.AnimationUtils.loadAnimation(view.getContext(), animation));
            view.setVisibility(View.GONE);
        }
    }

    public static void showTvTop(View tv) {
        if (tv != null && !tv.isShown()) {
            tv.setVisibility(View.VISIBLE);
            AlphaAnimation al = new AlphaAnimation(0f, 1f);
            TranslateAnimation ta = new TranslateAnimation(0f, 0f, 100f, 0f);
            AnimationSet set = new AnimationSet(true);
            set.addAnimation(al);
            set.addAnimation(ta);
            set.setDuration(300);
            tv.startAnimation(set);
        }
    }

    public static void hidTvTop(View tv) {
        if (tv != null && tv.isShown()) {
            tv.setVisibility(View.GONE);
            AlphaAnimation al = new AlphaAnimation(1f, 0f);
            TranslateAnimation ta = new TranslateAnimation(0f, 0f, 0f, 100f);
            AnimationSet set = new AnimationSet(true);
            set.addAnimation(al);
            set.addAnimation(ta);
            set.setDuration(300);
            tv.startAnimation(set);
        }
    }

    public final static void showWinByFade(Activity act) {
        if (act != null) {
            act.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    public final static void showAnimaNum(int startNum, int currNum, final TextView tv) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(startNum, currNum);
        valueAnimator.setDuration(500);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (Integer) animation.getAnimatedValue();
                tv.setText(String.valueOf(value));
            }
        });
        valueAnimator.start();
    }

    // 旋转动画
    public static void rotateAnim(View view) {
        RotateAnimation rotate = new RotateAnimation(0, 360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(500);
        view.startAnimation(rotate);
    }

    // 从左到右
    public static void anim_left2right(View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 1, 1);
        scaleAnimation.setDuration(300);
        view.startAnimation(scaleAnimation);
    }

    // 从右到左
    public static void anim_right2left(View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 1, 1,
                Animation.RELATIVE_TO_SELF, 1,
                Animation.RELATIVE_TO_SELF, 0.5f);

        scaleAnimation.setDuration(300);
        view.startAnimation(scaleAnimation);
    }

    // 从左到右
    public static void r2l(final View view) {
        final TranslateAnimation animation = new TranslateAnimation(1080, 0, 0, 0);
        animation.setDuration(2000);
        animation.setRepeatMode(Animation.REVERSE);//设置反方向执行
        view.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final TranslateAnimation animation = new TranslateAnimation(0, -1080, 0, 0);
                animation.setDuration(1000);
                animation.setRepeatMode(Animation.REVERSE);//设置反方向执行

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        view.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                view.startAnimation(animation);
            }
        }, 3000);

    }


    // 从上到下
    public static void anim_up2down(View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1, 0, 1);
        scaleAnimation.setDuration(300);
        view.startAnimation(scaleAnimation);
    }

    // 翻转动画
    public static void signInAnim1(final View view) {
        ScaleAnimation sato0 = new ScaleAnimation(1, 0, 1, 1,
                Animation.RELATIVE_TO_PARENT, 0.5f,
                Animation.RELATIVE_TO_PARENT, 0.5f);
        sato0.setDuration(300);
        view.startAnimation(sato0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                signInAnim2(view);
            }
        }, 300);// 200动画的时间
    }

    // 翻转动画
    private static void signInAnim2(View view) {
        ScaleAnimation sato1 = new ScaleAnimation(0, 1, 1, 1,
                Animation.RELATIVE_TO_PARENT, 0.5f,
                Animation.RELATIVE_TO_PARENT, 0.5f);
        sato1.setDuration(300);
        view.startAnimation(sato1);
    }
}
