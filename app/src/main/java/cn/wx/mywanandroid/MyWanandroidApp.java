package cn.wx.mywanandroid;

import android.app.Application;
import android.content.Context;

import cn.wx.support_base.wanandroidkit.WXHttp;

public class MyWanandroidApp extends Application {

    private static MyWanandroidApp app;

    @Override
    public void onCreate() {
        super.onCreate();

        WXHttp.init(this);
        //WXLog.init(this);
        app = this;
    }

    public static Context getApp() {
        return app.getApplicationContext();
    }
}
