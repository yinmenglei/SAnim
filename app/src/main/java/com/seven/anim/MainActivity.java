package com.seven.anim;

import android.animation.Animator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.seven.anim.util.AnimationUtils;
import com.seven.anim.widget.FlowLikeView;
import com.seven.anim.widget.SlideSwitcher;


public class MainActivity extends AppCompatActivity {
    private ToggleButton toggleButton;
    private SlideSwitcher mSlideSwitcher;
    private FlowLikeView likeViewLayout;
    private TextView tvTipMsg, tvMsg;
    private ImageView ivGif;
    private ImageView ivLiveIng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setImage();
    }

    private void initView() {
        likeViewLayout = findViewById(R.id.flowLikeView);
        tvTipMsg = findViewById(R.id.tv_tip_msg);
        tvMsg = findViewById(R.id.tv_msg);
        ivGif = findViewById(R.id.iv_gif);
        ivLiveIng = findViewById(R.id.iv_live_ing);

        toggleButton = findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "isChecked=" + isChecked, 1000).show();

                if (isChecked) {
                } else {
                }
            }
        });

        mSlideSwitcher = findViewById(R.id.btn_switch);

        mSlideSwitcher.setOpen(true);
        mSlideSwitcher.setSlideListener(new SlideSwitcher.SlideListener() {
            @Override
            public void onStatusChanged(SlideSwitcher view, boolean isOpen) {
                Toast.makeText(MainActivity.this, "isOpen=" + isOpen, 1000).show();
                if (isOpen) {

                } else {

                }
            }
        });

    }

    private void setImage() {
        Glide.with(this).asGif().load(R.mipmap.floatview_playing).into(ivGif);

        ((AnimationDrawable) ivLiveIng.getBackground()).start();
    }

    // 从左到右-抖动-渐变消失
    public void right2Left(View view) {
        tvMsg.setVisibility(View.VISIBLE);
        AnimationUtils.r2l(tvMsg);
    }

    // 从右到左-暂停2S-左边小时
    public void left2Right(View view) {
        tvTipMsg.setVisibility(View.VISIBLE);
        AnimationUtils.l2r(tvTipMsg);
    }

    public void addLikeView(View view) {
        likeViewLayout.addLikeView();
    }

    public void likeView(View view) {
        final LottieAnimationView lottieLike = findViewById(R.id.lottie_like);
        if (lottieLike == null) {
            return;
        }
        lottieLike.setVisibility(View.VISIBLE);
        lottieLike.playAnimation();
        lottieLike.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                lottieLike.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                lottieLike.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

}
