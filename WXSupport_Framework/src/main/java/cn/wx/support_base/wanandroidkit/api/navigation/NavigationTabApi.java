package cn.wx.support_base.wanandroidkit.api.navigation;

import androidx.annotation.NonNull;

import com.hjq.http.config.IRequestApi;

import cn.wx.support_base.wanandroidkit.base.WanAndroidApi;

public class NavigationTabApi implements IRequestApi {

    @NonNull
    @Override
    public String getApi() {
        return WanAndroidApi.NAV_DATA;
    }
}
