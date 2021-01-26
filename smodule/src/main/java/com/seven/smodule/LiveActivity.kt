package com.seven.smodule

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.seven.basemodule.BaseActivity
import com.seven.basemodule.BaseConstant


@Route(path = BaseConstant.B_MODULE_PATH)
class LiveActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smodule)

    }
}
