package cn.wx.mywanandroid.domain.publics;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.hjq.http.listener.OnHttpListener;

import java.util.ArrayList;
import java.util.List;

import cn.wx.mywanandroid.base.domain.RequesterModel;
import cn.wx.mywanandroid.data.bean.publics.PublicTabArticleDataItem;
import cn.wx.mywanandroid.data.bean.publics.PublicTabArticlesBean;
import cn.wx.support_base.wanandroidkit.WXHttp;
import cn.wx.support_base.wanandroidkit.api.publics.PublicHistoryApi;

public class PublicItemRequesterModel extends RequesterModel {

    public MutableLiveData<List<PublicTabArticleDataItem>> tabArticleItems = new MutableLiveData<>();

    private int totalPublicPageSize = 0;
    public List<PublicTabArticleDataItem> tabArticleDataItemList = new ArrayList<>();

    public interface IHttpCallback {
        void onComplete();
        void onFail(Exception e);
    }
    public void requestPublicArticlesByTab(LifecycleOwner owner, int tabId, boolean isReset, IHttpCallback callback) {
        if (isReset) {
            totalPublicPageSize = 0;
        }
        tabArticleDataItemList.clear();
        WXHttp.get(owner).api(String.format(new PublicHistoryApi().getApi(), tabId, totalPublicPageSize))
                .request(new OnHttpListener<PublicTabArticlesBean>() {
                    @Override
                    public void onHttpSuccess(PublicTabArticlesBean result) {
                        List<PublicTabArticleDataItem> tabArticleDataItems = result.getData().getDatas();
                        Log.w("WANG", "[requestPublicArticlesByTab].onHttpSuccess() tabArticleDataItems="+ tabArticleDataItems);
                        tabArticleDataItemList.addAll(tabArticleDataItems);
                        tabArticleItems.setValue(tabArticleDataItemList);
                        if (callback != null) {
                            callback.onComplete();
                        }
                    }

                    @Override
                    public void onHttpFail(Exception e) {
                        if (callback != null) {
                            callback.onFail(e);
                        }
                        Log.e("WANG", "[requestPublicArticlesByTab].onHttpFail() " + e.getMessage());
                        e.printStackTrace();
                    }
                });
    }

    public void addTotalPublicPageSize() {
        totalPublicPageSize++;
    }
}
