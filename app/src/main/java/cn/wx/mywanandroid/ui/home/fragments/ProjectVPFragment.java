package cn.wx.mywanandroid.ui.home.fragments;

import android.content.Context;
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
import cn.wx.mywanandroid.data.bean.project.ProjectArticleDataItem;
import cn.wx.mywanandroid.databinding.ProjectArticleLayoutBinding;
import cn.wx.mywanandroid.domain.project.ProjectDataRequesterModel;
import cn.wx.mywanandroid.ui.home.adapters.ProjectRVAdapter;

public class ProjectVPFragment extends Fragment {

    private final String TAG = ProjectVPFragment.class.getSimpleName();
    private ProjectArticleLayoutBinding mBinding;
    private Context context;
    private ProjectRVAdapter rvAdapter;
    private int tabId;
    private List<ProjectArticleDataItem> articleDataItemList = new ArrayList<>();
    private ProjectDataRequesterModel requesterModel;
    private ProjectVPFragmentStateModel vpFragmentStateModel;

    private ProjectVPFragment(Context context, int tabId){
        this.context = context;
        this.tabId = tabId;
    }

    public static ProjectVPFragment newInstance(Context context, int tabId) {
        return new ProjectVPFragment(context, tabId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.project_article_layout, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Log.d("WANG", "[ProjectVPFragment].onViewCreated()");
        initModel();
        initRV();
    }

    private void initModel() {
        vpFragmentStateModel = new ViewModelProvider(this).get(ProjectVPFragmentStateModel.class);
        requesterModel = new ViewModelProvider(this).get(ProjectDataRequesterModel.class);
        requesterModel.tabArticleList.observe(getViewLifecycleOwner(), new Observer<List<ProjectArticleDataItem>>() {
            @Override
            public void onChanged(List<ProjectArticleDataItem> projectArticleDataItems) {
                Log.w("WANG", TAG + ".[initModel].onChanged ==> "+projectArticleDataItems.size());
                articleDataItemList.addAll(projectArticleDataItems);
                rvAdapter.notifyDataSetChanged();
            }
        });

        requesterModel.requestProjectTabArticleList(this, tabId, true);
    }

    private void initRV() {
        rvAdapter = new ProjectRVAdapter(articleDataItemList, getActivity());
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.projectRv.setLayoutManager(llm);
        mBinding.projectRv.setAdapter(rvAdapter);

        mBinding.projectRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                requesterModel.requestProjectTabArticleList(getActivity(), tabId, true);
                refreshLayout.finishRefresh();
            }
        });

        mBinding.projectRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                requesterModel.requestProjectTabArticleList(getActivity(), tabId, false);
                refreshLayout.finishLoadMore();
            }
        });
    }

    public static class ProjectVPFragmentStateModel extends StateModel {

    }
}
