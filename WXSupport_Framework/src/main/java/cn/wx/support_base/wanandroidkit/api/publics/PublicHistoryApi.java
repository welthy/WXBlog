package cn.wx.support_base.wanandroidkit.api.publics;

import androidx.annotation.NonNull;

import com.hjq.http.config.IRequestApi;

import cn.wx.support_base.wanandroidkit.base.WanAndroidApi;

public class PublicHistoryApi implements IRequestApi {

    @NonNull
    @Override
    public String getApi() {
        return WanAndroidApi.PUBLIC_HISTORY;
    }
}
