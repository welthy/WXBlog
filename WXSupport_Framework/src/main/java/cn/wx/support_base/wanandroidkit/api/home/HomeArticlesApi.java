package cn.wx.support_base.wanandroidkit.api.home;

import androidx.annotation.NonNull;

import com.hjq.http.config.IRequestApi;

import cn.wx.support_base.wanandroidkit.base.WanAndroidApi;

/**
 * @author 小昕
 * 首页文章列表API，和结果bean
 */
public class HomeArticlesApi implements IRequestApi {
    @NonNull
    @Override
    public String getApi() {
        return WanAndroidApi.ARTICLE_LIST;
    }


}
