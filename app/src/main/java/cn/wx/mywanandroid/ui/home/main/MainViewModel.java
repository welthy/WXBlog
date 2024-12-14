package cn.wx.mywanandroid.ui.home.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import cn.wx.mywanandroid.base.ui.StateModel;

public class MainViewModel extends StateModel {

    MutableLiveData<String> title = new MutableLiveData<>();

    public MainViewModel(SavedStateHandle handle) {
    }


}
