package com.seven.anim.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.seven.anim.R;


public class LikeProgressBarActivity extends AppCompatActivity {

    private TextView tvProgress;

    private Handler myHandler;
    private int pvalue;

    private boolean canSyncing = true;
    private int increaseReta = 3;
    private int sleepTime = 100;


    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_progress);

        tvProgress = findViewById(R.id.tvProgress);

        //在自定义Handler类中，重写handleMessage（）方法
        //通过Message的值更新进度和提示信息
        myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                tvProgress.setText((msg.arg1 + "%"));

                if (msg.what == 0x222) {
                    Toast.makeText(LikeProgressBarActivity.this, "完成", Toast.LENGTH_SHORT).show();
                }
            }
        };

        initViewAction();
    }

    private void initViewAction() {
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvProgress.setText("");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        pvalue = 0;
                        while (canSyncing) {
                            pvalue += (int) (Math.random() * increaseReta);

                            try {
                                Thread.sleep(sleepTime);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            Message mes = new Message();
                            if (pvalue < 100) {
                                mes.arg1 = pvalue;
                                mes.what = 0x111;
                                myHandler.sendMessage(mes);
                            } else {
                                mes.arg1 = 100;
                                mes.what = 0x222;
                                myHandler.sendMessage(mes);
                                break;
                            }
                        }
                    }
                }).start();
            }
        });
    }


}
