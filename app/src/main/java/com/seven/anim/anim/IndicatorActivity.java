package com.seven.anim.anim;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.seven.anim.R;
import com.seven.anim.anim.avi.AVLoadingIndicatorView;


public class IndicatorActivity extends AppCompatActivity {

    private AVLoadingIndicatorView avi, avi2, avi3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator);

        String indicator = getIntent().getStringExtra("indicator");
        avi = findViewById(R.id.avi);
        avi2 = findViewById(R.id.avi_large);
        avi3 = findViewById(R.id.avi_small);

        avi.setIndicator(indicator);
        avi2.setIndicator(indicator);
        avi3.setIndicator(indicator);
    }

    public void hideClick(View view) {
        avi.hide();
        // or avi.smoothToHide();
    }

    public void showClick(View view) {
        avi.show();
        // or avi.smoothToShow();
    }
}
