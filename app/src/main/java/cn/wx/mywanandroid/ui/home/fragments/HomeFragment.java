package cn.wx.mywanandroid.ui.home.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.wx.mywanandroid.base.ui.StateModel;
import cn.wx.mywanandroid.data.bean.home.HomeDataBean;
import cn.wx.mywanandroid.databinding.FragmentHomeBinding;
import cn.wx.mywanandroid.domain.home.HomeRequesterModel;
import cn.wx.mywanandroid.ui.home.adapters.HomeRVAdapter;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding homeBinding;

    private HomeFragmentViewModel homeFragmentViewModel;
    private HomeRequesterModel homeRequesterModel;

    private HomeRVAdapter rvAdapter;
    private List<HomeDataBean> homeDataBeanList;

    public HomeFragment() {}

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.homeBinding = FragmentHomeBinding.inflate(inflater);
        return this.homeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("WANG", "[HomeFragment].onViewCreated()");
        initRV();
        initViewModel();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (homeDataBeanList != null && homeDataBeanList.size() == 0) {
            refreshBannerData();
            refreshData();
        }
    }

    private void initViewModel() {
        homeFragmentViewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);
        homeRequesterModel = new ViewModelProvider(this).get(HomeRequesterModel.class);

        homeRequesterModel.homeDataItem.observe(getViewLifecycleOwner(), new Observer<List<HomeDataBean>>() {
            @Override
            public void onChanged(List<HomeDataBean> result) {
                Log.w("WANG", "HomeFragment [initViewModel].onChange() result="+ Arrays.toString(result.toArray()));
                if (homeDataBeanList != null) {
                    homeDataBeanList.clear();
                    homeDataBeanList.addAll(result);
                    Log.d("WANG", "HomeFragment [initViewModel] notifyDataSetChanged");
                    rvAdapter.notifyDataSetChanged();
                }
            }
        });
        homeFragmentViewModel.isLoadMore.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

            }
        });
        homeFragmentViewModel.isRefresh.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

            }
        });
    }

    private void initRV() {
        homeDataBeanList = new ArrayList<>();
        rvAdapter = new HomeRVAdapter(homeDataBeanList);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        homeBinding.homeRv.setLayoutManager(llm);
        homeBinding.homeRv.setAdapter(rvAdapter);

        homeBinding.homeSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //下拉刷新时，需要同步刷新Banner数据，因为Banner数据是和列表同一个RV
                //refreshBannerData();
                refreshData();
                refreshLayout.finishRefresh();
            }
        });
        homeBinding.homeSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadMoreData();
                refreshLayout.finishLoadMore();
            }
        });
    }

    private void refreshBannerData() {
        homeRequesterModel.requestBannerData(this);
    }

    private void refreshData() {
        homeRequesterModel.requestArticles(this, 0, true);
    }

    private void loadMoreData() {
        int currentPage = homeRequesterModel.getCurrentTotalPage();
        homeRequesterModel.requestArticles(this, ++currentPage, false);
    }

    public static class HomeFragmentViewModel extends StateModel {
        public MutableLiveData<Boolean> isRefresh = new MutableLiveData<>();
        public MutableLiveData<Boolean> isLoadMore = new MutableLiveData<>();
    }
}