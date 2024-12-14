package cn.wx.mywanandroid.domain.home;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.hjq.http.listener.OnHttpListener;

import java.util.ArrayList;
import java.util.List;

import cn.wx.mywanandroid.base.domain.RequesterModel;
import cn.wx.mywanandroid.data.bean.home.ArticleData;
import cn.wx.mywanandroid.data.bean.home.BannerData;
import cn.wx.mywanandroid.data.bean.home.HomeDataBean;
import cn.wx.support_base.wanandroidkit.WXHttp;
import cn.wx.support_base.wanandroidkit.api.home.HomeArticlesApi;
import cn.wx.support_base.wanandroidkit.api.home.HomeBannerApi;

public class HomeRequesterModel extends RequesterModel {

    private final String TAG = HomeRequesterModel.class.getSimpleName();
    public MutableLiveData<List<HomeDataBean>> homeDataItem = new MutableLiveData<>();

    private int totalPage = 0;
    private List<HomeDataBean> homeDataBeanList = new ArrayList<>();

    public void requestArticles(LifecycleOwner lifecycleOwner, int page, boolean isReset) {
        if (isReset) {
            homeDataBeanList.clear();
        }
        totalPage = page;
        if (totalPage > 40) {
            totalPage = 40;
        } else if (totalPage < 0) {

            return;
        }
        String api = String.format(new HomeArticlesApi().getApi(), totalPage);
        WXHttp.get(lifecycleOwner)
                .api(api)
                .request(new OnHttpListener<ArticleData>() {
                    @Override
                    public void onHttpSuccess(ArticleData result) {
                        Log.w("WANG", "[requestArticles].onHttpSuccess() ==> " + result.toString());
                        List<ArticleData.DatasItem> dataItems = result.getData().getDatas();
                        for (ArticleData.DatasItem data : dataItems) {
                            HomeDataBean homeDataBean = new HomeDataBean();
                            homeDataBean.setArticleData(data);
                            homeDataBeanList.add(homeDataBean);
                        }
                        homeDataItem.setValue(homeDataBeanList);
                    }

                    @Override
                    public void onHttpFail(Exception e) {
                        Log.e(TAG, "[requestArticles] fail. " + e.getMessage());
                        e.printStackTrace();
                    }
                });
    }

    public int getCurrentTotalPage() {
        return this.totalPage;
    }

    public void requestBannerData(LifecycleOwner owner) {
        WXHttp.get(owner)
                .api(new HomeBannerApi().getApi())
                .request(new OnHttpListener<BannerData>() {
            @Override
            public void onHttpSuccess(BannerData result) {
                Log.w("WANG", "[requestBannerData].onHttpSuccess() ==> " + result.toString());
                HomeDataBean homeDataBean = new HomeDataBean();
                homeDataBean.setBannerDatas(result);
                homeDataBeanList.add(homeDataBean);
                //homeDataItem.setValue(homeDataBeanList);
            }

            @Override
            public void onHttpFail(Exception e) {
                Log.e(TAG, "[requestBannerData] onHttpFail. " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

}
