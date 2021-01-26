package com.seven.amodule

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.seven.basemodule.BaseActivity
import com.seven.basemodule.BaseConstant


@Route(path = BaseConstant.A_MODULE_PATH)
class CourseActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amodule)
    }
}
