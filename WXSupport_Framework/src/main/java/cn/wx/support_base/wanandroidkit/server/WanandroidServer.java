package cn.wx.support_base.wanandroidkit.server;

import androidx.annotation.NonNull;

import com.hjq.http.config.IRequestServer;

import cn.wx.support_base.wanandroidkit.base.WanAndroidApi;

/**
 * @author 小昕
 * wanandroid服务器,提供基础域名
 */
public class WanandroidServer implements IRequestServer {

    @NonNull
    @Override
    public String getHost() {
        return WanAndroidApi.BASE_URL;
    }
}
