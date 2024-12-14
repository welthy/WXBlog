package cn.wx.mywanandroid.domain.navigation;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.hjq.http.listener.OnHttpListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wx.mywanandroid.base.domain.RequesterModel;
import cn.wx.mywanandroid.data.bean.navigation.NavigationTabArticlesItem;
import cn.wx.mywanandroid.data.bean.navigation.NavigationTabDataItem;
import cn.wx.mywanandroid.data.bean.navigation.NavigationTabResponse;
import cn.wx.support_base.wanandroidkit.WXHttp;
import cn.wx.support_base.wanandroidkit.api.navigation.NavigationTabApi;

public class NavigationRequester extends RequesterModel {

    public NavigationRequester(SavedStateHandle handle) {
    }

    public MutableLiveData<List<NavigationTabDataItem>> tabDataItemList = new MutableLiveData<>();
    public MutableLiveData<List<Map<String, List<NavigationTabArticlesItem>>>> tabArticlesList = new MutableLiveData<>();

    public List<NavigationTabDataItem> tabItemList = new ArrayList<>();
    public List<Map<String, List<NavigationTabArticlesItem>>> tabArticlesItemList = new ArrayList<>();

    public void requestTabListData(LifecycleOwner owner) {
        tabItemList.clear();
        tabArticlesItemList.clear();
        WXHttp.get(owner).api(new NavigationTabApi().getApi()).request(new OnHttpListener<NavigationTabResponse>() {

            @Override
            public void onHttpSuccess(NavigationTabResponse result) {
                List<NavigationTabDataItem> tabDataItems = result.getData();

                for (int i=0; i<tabDataItems.size(); i++) {
                    NavigationTabDataItem tabDataItem = tabDataItems.get(i);
                    if (tabDataItem.getArticles().size() > 0) {
                        Log.d("WANG", "[requestTabListData], tab="+tabDataItem.getName()+" ==> articles="+tabDataItem.getArticles().get(0).getTitle());
                        tabItemList.add(tabDataItem);
                        List<NavigationTabArticlesItem> tabArticlesItems = tabDataItem.getArticles();
                        Map<String, List<NavigationTabArticlesItem>> articleMap = new HashMap<>();
                        articleMap.put(tabDataItem.getName(), tabArticlesItems);
                        tabArticlesItemList.add(articleMap);
                    }
                }
                tabDataItemList.postValue(tabItemList);
                //tabArticlesList.postValue(tabArticlesItemList);

            }

            @Override
            public void onHttpFail(Exception e) {

            }
        });
    }
}
