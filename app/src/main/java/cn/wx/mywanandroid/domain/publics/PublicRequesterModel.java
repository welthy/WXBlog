package cn.wx.mywanandroid.domain.publics;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.hjq.http.listener.OnHttpListener;

import java.util.ArrayList;
import java.util.List;

import cn.wx.mywanandroid.base.domain.RequesterModel;
import cn.wx.mywanandroid.data.bean.publics.PublicTabBean;
import cn.wx.mywanandroid.data.bean.publics.PublicTabDataItem;
import cn.wx.support_base.wanandroidkit.WXHttp;
import cn.wx.support_base.wanandroidkit.api.publics.PublicTabListApi;

public class PublicRequesterModel extends RequesterModel {

    public MutableLiveData<List<PublicTabDataItem>> publicTabItem = new MutableLiveData<>();

    public List<PublicTabDataItem> dataItemList = new ArrayList<>();



    public void requestPublicTabList(LifecycleOwner owner) {
        WXHttp.get(owner).api(new PublicTabListApi().getApi())
                .request(new OnHttpListener<PublicTabBean>() {
            @Override
            public void onHttpSuccess(PublicTabBean result) {
                List<PublicTabDataItem> publicDataItemList = result.getData();
                dataItemList.addAll(publicDataItemList);
                publicTabItem.setValue(dataItemList);
            }

            @Override
            public void onHttpFail(Exception e) {
                Log.e("WANG", "[requestPublicTabList].onHttpFail() " + e.getMessage());
                e.printStackTrace();
            }
        });
    }


}
