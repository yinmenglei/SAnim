package com.seven.basemodule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter

open class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //在Activity/Fragment类里面进入Arouter 注入
        ARouter.getInstance().inject(this)
    }
}