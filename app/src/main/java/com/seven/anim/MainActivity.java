package com.seven.anim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.seven.anim.activity.CustomAnimActivity;
import com.seven.anim.activity.InputFilterActivity;
import com.seven.anim.activity.LikeProgressBarActivity;
import com.seven.anim.activity.RoundViewActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void customAnim(View view) {
        startActivity(new Intent(this, CustomAnimActivity.class));
    }

    public void roundView(View view) {
        startActivity(new Intent(this, RoundViewActivity.class));
    }

    public void editTextInput(View view) {
        startActivity(new Intent(this, InputFilterActivity.class));
    }

    public void likeProgressBar(View view) {
        startActivity(new Intent(this, LikeProgressBarActivity.class));
    }
}