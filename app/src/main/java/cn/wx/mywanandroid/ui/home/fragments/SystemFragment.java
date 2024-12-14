package cn.wx.mywanandroid.ui.home.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import cn.wx.mywanandroid.R;
import cn.wx.mywanandroid.base.ui.StateModel;
import cn.wx.mywanandroid.data.bean.system.SystemDataItem;
import cn.wx.mywanandroid.databinding.FragmentSystemBinding;
import cn.wx.mywanandroid.domain.system.SystemRequesterModel;
import cn.wx.mywanandroid.ui.home.adapters.SystemRVAdapter;

public class SystemFragment extends Fragment {

    private FragmentSystemBinding mBinding;
    private SystemRVAdapter rvAdapter;
    private List<SystemDataItem> systemDataItemList = new ArrayList<>();

    private SystemRequesterModel systemRequesterModel;
    private SystemStateModel systemStateModel;

    public SystemFragment() {}
    public static SystemFragment newInstance() {
        return new SystemFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_system, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Log.d("WANG", "[SystemFragment].onViewCreated()");
        initModel();
        initRV();
    }

    @Override
    public void onResume() {
        super.onResume();
        systemRequesterModel.requesterSystemData(getActivity());
    }

    private void initModel() {
        systemRequesterModel = new ViewModelProvider(this).get(SystemRequesterModel.class);
        systemStateModel = new ViewModelProvider(this).get(SystemStateModel.class);
        systemRequesterModel.dataItemModel.observe(getViewLifecycleOwner(), new Observer<List<SystemDataItem>>() {
            @Override
            public void onChanged(List<SystemDataItem> systemDataItems) {
                systemDataItemList.clear();
                systemDataItemList.addAll(systemDataItems);
                rvAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initRV() {
        rvAdapter = new SystemRVAdapter(getActivity(), systemDataItemList);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        mBinding.systemRv.setLayoutManager(llm);
        mBinding.systemRv.setAdapter(rvAdapter);

        mBinding.systemRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                systemRequesterModel.requesterSystemData(getActivity());
                refreshLayout.finishRefresh();
            }
        });
        mBinding.systemRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                systemRequesterModel.requesterSystemData(getActivity());
                refreshLayout.finishLoadMore();
            }
        });
    }

    public static class SystemStateModel extends StateModel {

    }
}