package cn.wx.support_base.wanandroidkit.api.system;

import androidx.annotation.NonNull;

import com.hjq.http.config.IRequestApi;

import cn.wx.support_base.wanandroidkit.base.WanAndroidApi;

public class SystemApi implements IRequestApi {

    @NonNull
    @Override
    public String getApi() {
        return WanAndroidApi.STRUCTURE_DATA;
    }
}
