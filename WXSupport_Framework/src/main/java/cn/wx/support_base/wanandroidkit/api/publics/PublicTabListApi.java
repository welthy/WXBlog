package cn.wx.support_base.wanandroidkit.api.publics;

import androidx.annotation.NonNull;

import com.hjq.http.config.IRequestApi;

import cn.wx.support_base.wanandroidkit.base.WanAndroidApi;

public class PublicTabListApi implements IRequestApi {

    @NonNull
    @Override
    public String getApi() {
        return WanAndroidApi.PUBLIC_LIST;
    }
}
