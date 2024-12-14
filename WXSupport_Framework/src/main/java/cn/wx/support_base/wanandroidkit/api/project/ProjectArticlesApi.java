package cn.wx.support_base.wanandroidkit.api.project;

import androidx.annotation.NonNull;

import com.hjq.http.config.IRequestApi;

import cn.wx.support_base.wanandroidkit.base.WanAndroidApi;

public class ProjectArticlesApi implements IRequestApi {
    @NonNull
    @Override
    public String getApi() {
        return WanAndroidApi.PROJECT_LIST_DATA;
    }
}
