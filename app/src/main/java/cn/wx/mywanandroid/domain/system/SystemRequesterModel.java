package cn.wx.mywanandroid.domain.system;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.hjq.http.listener.OnHttpListener;

import java.util.ArrayList;
import java.util.List;

import cn.wx.mywanandroid.base.domain.RequesterModel;
import cn.wx.mywanandroid.data.bean.system.SystemDataItem;
import cn.wx.mywanandroid.data.bean.system.SystemDataResponse;
import cn.wx.support_base.wanandroidkit.WXHttp;
import cn.wx.support_base.wanandroidkit.api.system.SystemApi;

public class SystemRequesterModel extends RequesterModel {

    public MutableLiveData<List<SystemDataItem>> dataItemModel = new MediatorLiveData<>();
    List<SystemDataItem> systemDataItemList = new ArrayList<>();

    public void requesterSystemData(LifecycleOwner owner) {
        systemDataItemList.clear();
        WXHttp.get(owner).api(new SystemApi()).request(new OnHttpListener<SystemDataResponse>() {
            @Override
            public void onHttpSuccess(SystemDataResponse result) {
                List<SystemDataItem> dataItems = result.getData();
                systemDataItemList.addAll(dataItems);
                dataItemModel.postValue(systemDataItemList);
            }

            @Override
            public void onHttpFail(Exception e) {
                Log.e("WANG", "[SystemRequesterModel].onHttpFail() " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
