package com.seven.anim;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class SApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initARouter();
    }



    private void initARouter() {
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}
