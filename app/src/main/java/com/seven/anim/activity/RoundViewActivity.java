package com.seven.anim.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.flyco.roundview.RoundTextView;
import com.seven.anim.R;
import com.seven.anim.widget.InputFilterEditText;

public class RoundViewActivity extends AppCompatActivity {

    RoundTextView roundTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_view);

        initView();
    }


    private void initView() {

    }

}
