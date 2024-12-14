package cn.wx.mywanandroid.utils;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDelegate;

public class WindowUtil {

    public static void transparentStatusBar(Window window, int statusBarColor) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
        systemUiVisibility = systemUiVisibility |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        window.getDecorView().setSystemUiVisibility(systemUiVisibility);
        window.setStatusBarColor(statusBarColor);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        //设置状态栏文字颜色
        setStatusBarTextColor(window, AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO);
    }

    public static void setStatusBarTextColor(Window window, boolean light) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
            if (light) { //白色文字
                systemUiVisibility = ~(systemUiVisibility & View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else { //黑色文字
                systemUiVisibility = systemUiVisibility | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            window.getDecorView().setSystemUiVisibility(systemUiVisibility);
        }
    }
    public static int getStatusBarHeight(Context context) {
        int resId = context.getResources().getIdentifier(
                "status_bar_height", "dimen", "android"
        );
        return context.getResources().getDimensionPixelSize(resId);
    }

    public static void paddingByStatusBar(View view) {
        view.setPadding(
                view.getPaddingLeft(),
                view.getPaddingTop() + getStatusBarHeight(view.getContext()),
                view.getPaddingRight(),
                view.getPaddingBottom()
        );
    }
}
