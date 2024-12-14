package cn.wx.support_base.wanandroidkit.api.home;

import androidx.annotation.NonNull;

import com.hjq.http.config.IRequestApi;

import cn.wx.support_base.wanandroidkit.base.WanAndroidApi;

public class HomeBannerApi implements IRequestApi {

    @NonNull
    @Override
    public String getApi() {
        return WanAndroidApi.HOME_BANNER;
    }
}
