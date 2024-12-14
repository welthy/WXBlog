package cn.wx.mywanandroid.domain.project;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.hjq.http.listener.OnHttpListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.wx.mywanandroid.base.domain.RequesterModel;
import cn.wx.mywanandroid.data.bean.project.ProjectArticleDataItem;
import cn.wx.mywanandroid.data.bean.project.ProjectArticlesResponse;
import cn.wx.mywanandroid.data.bean.project.ProjectTabDataItem;
import cn.wx.mywanandroid.data.bean.project.ProjectTabResponse;
import cn.wx.support_base.wanandroidkit.WXHttp;
import cn.wx.support_base.wanandroidkit.api.project.ProjectArticlesApi;
import cn.wx.support_base.wanandroidkit.api.project.ProjectTabApi;

public class ProjectDataRequesterModel extends RequesterModel {

    public MutableLiveData<List<ProjectTabDataItem>> tabList = new MutableLiveData<>();
    public MutableLiveData<List<ProjectArticleDataItem>> tabArticleList = new MutableLiveData<>();

    private ArrayList<ProjectTabDataItem> tabDataItemList = new ArrayList<>();
    private ArrayList<ProjectArticleDataItem> articleDataItemList = new ArrayList<>();

    private HashMap<Integer, Integer> pageWithTab = new HashMap<>();

    public ProjectDataRequesterModel(SavedStateHandle handle) {

    }

    public void requestProjectTabList(LifecycleOwner owner) {
        tabDataItemList.clear();
        WXHttp.get(owner).api(new ProjectTabApi()).request(new OnHttpListener<ProjectTabResponse>() {

            @Override
            public void onHttpSuccess(ProjectTabResponse result) {
                tabDataItemList.addAll(result.getData());
                tabList.postValue(tabDataItemList);
            }

            @Override
            public void onHttpFail(Exception e) {

            }
        });
    }

    public void requestProjectTabArticleList(LifecycleOwner owner, int tabId, boolean isReset) {
        int page = 1;
        if (isReset) {
            pageWithTab.put(tabId, page);

        } else {
            Integer pagetInteger = pageWithTab.get(tabId);
            if (pagetInteger != null) {
                page = pagetInteger;
            }
        }
        articleDataItemList.clear();
        String url = String.format(new ProjectArticlesApi().getApi(), page, tabId);
        WXHttp.get(owner).api(url).request(new OnHttpListener<ProjectArticlesResponse>() {
            @Override
            public void onHttpSuccess(ProjectArticlesResponse result) {
                List<ProjectArticleDataItem> dataItemList = result.getData().getDatas();
                tabArticleList.postValue(dataItemList);
            }

            @Override
            public void onHttpFail(Exception e) {

            }
        });
    }
}
