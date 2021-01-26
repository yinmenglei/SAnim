package com.seven.anim.router

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.seven.anim.R
import com.seven.anim.constant.AppConstant
import com.seven.basemodule.BaseActivity
import com.seven.basemodule.BaseConstant


@Route(path = AppConstant.APP_TEST_ROUTER)
class RouterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_router)
    }

    fun routerJump1(view: View?) {
        ARouter.getInstance()
                .build(BaseConstant.A_MODULE_PATH)
                .navigation()
    }

    fun routerJump2(view: View?) {
        ARouter.getInstance()
                .build(BaseConstant.A_MODULE_PATH)
                .withString("key1", "value1")
                .withInt("key2", 1)
                .navigation(this)
    }

    fun routerJump3(view: View?) {
        ARouter.getInstance()
                .build(BaseConstant.A_MODULE_PATH)
                .navigation(this, 100)
    }

    fun routerJump4(view: View?) {
        ARouter.getInstance()
                .build(AppConstant.APP_ROUND_VIEW)
                .navigation()


        ARouter.getInstance()
                .build(AppConstant.APP_ROUND_VIEW)
                .navigation(this, object : NavigationCallback {
                    override fun onFound(postcard: Postcard?) {
                        toast("onFound-路由目标发现")
                    }

                    override fun onLost(postcard: Postcard?) {
                        toast("onLost-路由目标丢失")
                    }

                    override fun onArrival(postcard: Postcard?) {
                        toast("onArrival-路由目标跳转")
                    }

                    override fun onInterrupt(postcard: Postcard?) {
                        toast("onInterrupt-路由目标被拦截")
                    }
                })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            toast("页面跳转回调")
        }
    }

    private fun toast(hints: String) {
        Toast.makeText(this, hints, Toast.LENGTH_LONG).show()
    }

}
