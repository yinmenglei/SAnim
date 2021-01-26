package com.seven.anim.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.flyco.roundview.RoundTextView;
import com.seven.anim.R;
import com.seven.anim.constant.AppConstant;


@Route(path = AppConstant.APP_ROUND_VIEW)
public class RoundViewActivity extends AppCompatActivity {

    RoundTextView roundTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_view);
    }

}
