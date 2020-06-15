package com.seven.anim;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.seven.anim.util.AnimationUtils;
import com.seven.anim.widget.FlowLikeView;


public class MainActivity extends AppCompatActivity {

    private FlowLikeView likeViewLayout;
    private TextView tvTipMsg;
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
        ivGif = findViewById(R.id.iv_gif);
        ivLiveIng = findViewById(R.id.iv_live_ing);
    }

    private void setImage() {
        Glide.with(this).asGif().load(R.mipmap.floatview_playing).into(ivGif);

        ((AnimationDrawable) ivLiveIng.getBackground()).start();
    }

    public void left2Right(View view) {
        tvTipMsg.setVisibility(View.VISIBLE);
        AnimationUtils.r2l(tvTipMsg);
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
