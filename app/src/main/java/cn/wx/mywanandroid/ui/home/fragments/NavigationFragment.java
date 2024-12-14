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
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.wx.mywanandroid.R;
import cn.wx.mywanandroid.base.ui.StateModel;
import cn.wx.mywanandroid.data.bean.navigation.NavigationTabDataItem;
import cn.wx.mywanandroid.databinding.FragmentNavigationBinding;
import cn.wx.mywanandroid.domain.navigation.NavigationRequester;
import cn.wx.mywanandroid.ui.home.adapters.NavigationRVAdapter;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.TabView;

public class NavigationFragment extends Fragment implements VerticalTabLayout.OnTabSelectedListener {

    private FragmentNavigationBinding mBinding;
    private NavigationRequester mRequester;
    private NavigationFragmentStateModel stateModel;
    private List<NavigationTabDataItem> tabDataItems = new ArrayList<>();
    private NavigationRVAdapter rvAdapter;
    private  LinearLayoutManager llm;
    private volatile boolean isNeedScroll;
    private int mTopPosition;

    public NavigationFragment() {}
    public static NavigationFragment newInstance() {
        return new NavigationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_navigation, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("WANG", "[NavigationFragment].onViewCreated()");
        initModel();
        initTab();
    }

    @Override
    public void onResume() {
        super.onResume();
        mRequester.requestTabListData(this);
    }

    @Override
    public void onTabSelected(TabView tab, int position) {
        smoothScrollToPositionOfRV(position);
    }

    @Override
    public void onTabReselected(TabView tab, int position) {

    }

    private void initModel() {
        mRequester = new ViewModelProvider(this).get(NavigationRequester.class);
        stateModel = new ViewModelProvider(this).get(NavigationFragmentStateModel.class);

        mRequester.tabDataItemList.observe(getViewLifecycleOwner(), new Observer<List<NavigationTabDataItem>>() {
            @Override
            public void onChanged(List<NavigationTabDataItem> navigationTabDataItems) {
                mBinding.setRequester(mRequester);
                tabDataItems.clear();
                tabDataItems.addAll(navigationTabDataItems);
                initRV();
            }
        });
    }

    private void initTab() {
        mBinding.navigationTab.addOnTabSelectedListener(this);
    }

    private void initRV() {
        rvAdapter = new NavigationRVAdapter(getActivity(), tabDataItems);
        llm = new LinearLayoutManager(mBinding.getRoot().getContext());
        llm.setOrientation(RecyclerView.VERTICAL);
        mBinding.navigationRv.setLayoutManager(llm);
        mBinding.navigationRv.setAdapter(rvAdapter);
        mBinding.navigationRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (isNeedScroll) {
                        isNeedScroll = false;
                        smoothScrollToPositionOfRV(mTopPosition);
                    } else {
                        setTabSelected();
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
    }

    private void smoothScrollToPositionOfRV(int position) {
        int firstVisiblePosition = llm.findFirstVisibleItemPosition();
        int lastVisiblePosition = llm.findLastVisibleItemPosition();
        if (position < firstVisiblePosition) {
            mBinding.navigationRv.smoothScrollToPosition(position);
        } else if (position < lastVisiblePosition) {
            int movePos = position - firstVisiblePosition;
            if (movePos >= 0 && movePos < llm.getChildCount()) {
                mBinding.navigationRv.smoothScrollBy(0, llm.getChildAt(movePos).getTop());
            }
        } else {
            mBinding.navigationRv.smoothScrollToPosition(position);
            mTopPosition = position;
            isNeedScroll = true;
        }
    }

    private void setTabSelected() {
        int firstVisiblePos = llm.findFirstVisibleItemPosition();
        mBinding.navigationTab.setTabSelected(firstVisiblePos, false);
    }

    public static class NavigationFragmentStateModel extends StateModel {

    }
}