package cn.wx.mywanandroid.ui.home.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import cn.wx.mywanandroid.R;
import cn.wx.mywanandroid.base.ui.StateModel;
import cn.wx.mywanandroid.data.bean.publics.PublicTabArticleDataItem;
import cn.wx.mywanandroid.databinding.PublicItemFragmentLayoutBinding;
import cn.wx.mywanandroid.domain.publics.PublicItemRequesterModel;
import cn.wx.mywanandroid.ui.home.adapters.PublicRVAdapter;

public class PublicItemFragment extends Fragment {

    private PublicItemFragmentLayoutBinding mBinding;

    private PublicRVAdapter rvAdapter;
    private int tagId;
    private PublicItemRequesterModel mRequesterModel;
    private PublicItemStateModel mStateModel;
    private List<PublicTabArticleDataItem> tabDataItemList;

    private PublicItemFragment(int tagId) {
        this.tagId = tagId;
    }
    public static PublicItemFragment newInstance(int tagId) {
        return new PublicItemFragment(tagId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.public_item_fragment_layout, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Log.d("WANG", "[PublicItemFragment].onViewCreated()");
        initModel();
        initRV();
    }

    private void initModel() {
        mStateModel = new ViewModelProvider(this).get(PublicItemStateModel.class);
        mRequesterModel = new ViewModelProvider(this).get(PublicItemRequesterModel.class);

        mRequesterModel.tabArticleItems.observe(getViewLifecycleOwner(), new Observer<List<PublicTabArticleDataItem>>() {
            @Override
            public void onChanged(List<PublicTabArticleDataItem> publicTabArticleDataItems) {
                tabDataItemList.addAll(publicTabArticleDataItems);
                rvAdapter.notifyDataSetChanged();
            }
        });

        mRequesterModel.requestPublicArticlesByTab(getActivity(), tagId, true, null);
    }

    private void initRV() {
        tabDataItemList = new ArrayList<>();
        rvAdapter = new PublicRVAdapter(getActivity(), tabDataItemList);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(RecyclerView.VERTICAL);
        mBinding.publicItemRv.setLayoutManager(llm);
        mBinding.publicItemRv.setAdapter(rvAdapter);

        mBinding.itemRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRequesterModel.requestPublicArticlesByTab(getActivity(), tagId, true, new PublicItemRequesterModel.IHttpCallback() {
                    @Override
                    public void onComplete() {
                        refreshLayout.finishRefresh();
                    }

                    @Override
                    public void onFail(Exception e) {
                        refreshLayout.finishRefresh();
                        Toast.makeText(getActivity(), "刷新失败", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        mBinding.itemRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mRequesterModel.addTotalPublicPageSize();
                mRequesterModel.requestPublicArticlesByTab(getActivity(), tagId, false, new PublicItemRequesterModel.IHttpCallback() {
                    @Override
                    public void onComplete() {
                        refreshLayout.finishLoadMore();
                    }

                    @Override
                    public void onFail(Exception e) {
                        refreshLayout.finishLoadMore();
                        Toast.makeText(getActivity(), "加载失败", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public static class PublicItemStateModel extends StateModel {

    }
}

