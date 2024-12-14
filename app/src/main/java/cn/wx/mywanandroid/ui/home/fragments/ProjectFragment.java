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

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import cn.wx.mywanandroid.R;
import cn.wx.mywanandroid.base.adapter.CommonVPAdapter;
import cn.wx.mywanandroid.base.ui.StateModel;
import cn.wx.mywanandroid.data.bean.project.ProjectTabDataItem;
import cn.wx.mywanandroid.databinding.FragmentProjectBinding;
import cn.wx.mywanandroid.domain.project.ProjectDataRequesterModel;

public class ProjectFragment extends Fragment {

    private FragmentProjectBinding mBinding;
    private Context context;
    private ProjectStateModel stateModel;
    private ProjectDataRequesterModel requesterModel;
    private List<Fragment> vpFragments = new ArrayList<>();
    private CommonVPAdapter vpAdapter;
    private TabLayoutMediator tlm;

    public ProjectFragment() {
    }

    public static ProjectFragment newInstance() {
        return new ProjectFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_project,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("WANG", "[ProjectFragment].onViewCreated()");
        initModel();
    }

    @Override
    public void onResume() {
        super.onResume();
        requesterModel.requestProjectTabList(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tlm.detach();
    }

    private void initModel() {
        stateModel = new ViewModelProvider(this).get(ProjectStateModel.class);
        requesterModel = new ViewModelProvider(this).get(ProjectDataRequesterModel.class);

        requesterModel.tabList.observe(getViewLifecycleOwner(), new Observer<List<ProjectTabDataItem>>() {
            @Override
            public void onChanged(List<ProjectTabDataItem> projectTabDataItems) {
                initVP(projectTabDataItems);
                tlm = new TabLayoutMediator(mBinding.projectTab, mBinding.projectVp, new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(projectTabDataItems.get(position).getName());
                    }
                });
                tlm.attach();
            }
        });
    }

    private void initVP(List<ProjectTabDataItem> tabDataItemList) {
        if (vpFragments == null) {
            vpFragments = new ArrayList<>();
        }
        vpFragments.clear();
        for (ProjectTabDataItem tabDataItem : tabDataItemList) {
            ProjectVPFragment fragment = ProjectVPFragment.newInstance(getActivity(), tabDataItem.getId());
            vpFragments.add(fragment);
        }
        vpAdapter = new CommonVPAdapter(this, vpFragments);
        mBinding.projectVp.setAdapter(vpAdapter);
        mBinding.projectVp.setSaveEnabled(false);
    }


    public static class ProjectStateModel extends StateModel {

    }
}