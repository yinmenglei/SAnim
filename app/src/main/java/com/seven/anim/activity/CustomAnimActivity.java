package com.seven.anim.activity;

import android.animation.Animator;
import android.content.Intent;
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
import com.seven.anim.R;
import com.seven.anim.anim.SampleActivity;
import com.seven.anim.util.AnimationUtils;
import com.seven.anim.widget.FlowLikeView;
import com.seven.anim.widget.SlideSwitcher;

public class CustomAnimActivity extends AppCompatActivity {

    private FlowLikeView likeViewLayout;
    private TextView tvTipMsg, tvMsg;
    private ImageView ivGif;
    private ImageView ivLiveIng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_anim);

        initView();
        setImage();
    }

    private void initView() {
        likeViewLayout = findViewById(R.id.flowLikeView);
        tvTipMsg = findViewById(R.id.tv_tip_msg);
        tvMsg = findViewById(R.id.tv_msg);
        ivGif = findViewById(R.id.iv_gif);
        ivLiveIng = findViewById(R.id.iv_live_ing);

        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(CustomAnimActivity.this, "isChecked=" + isChecked, Toast.LENGTH_SHORT).show();
            }
        });

        SlideSwitcher mSlideSwitcher = findViewById(R.id.btn_switch);
        mSlideSwitcher.setOpen(true);
        mSlideSwitcher.setSlideListener(new SlideSwitcher.SlideListener() {
            @Override
            public void onStatusChanged(SlideSwitcher view, boolean isOpen) {
                Toast.makeText(CustomAnimActivity.this, "isOpen=" + isOpen, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setImage() {
        Glide.with(this).asGif().load(R.mipmap.floatview_playing).into(ivGif);

        ((AnimationDrawable) ivLiveIng.getBackground()).start();
    }

    public void childCustomAnim(View view) {
        startActivity(new Intent(this, SampleActivity.class));
    }

    // 从左到右-抖动-渐变消失
    public void right2Left(View view) {
        tvMsg.setVisibility(View.VISIBLE);
        AnimationUtils.r2l(tvMsg);
    }

    // 从右到左-暂停2S-左边消失
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
