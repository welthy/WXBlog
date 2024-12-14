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

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import cn.wx.mywanandroid.R;
import cn.wx.mywanandroid.base.adapter.CommonVPAdapter;
import cn.wx.mywanandroid.base.ui.StateModel;
import cn.wx.mywanandroid.data.bean.publics.PublicTabDataItem;
import cn.wx.mywanandroid.databinding.FragmentPublicBinding;
import cn.wx.mywanandroid.domain.publics.PublicRequesterModel;

/**
 *
 */
public class PublicFragment extends Fragment {

    private final String TAG = PublicFragment.class.getSimpleName();
    private FragmentPublicBinding mBinding;
    private PublicStateModel publicStateModel;
    private PublicRequesterModel publicRequesterModel;
    private CommonVPAdapter publicViewPagerAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();
    private TabLayoutMediator tabLayoutMediator;

    public PublicFragment() {}

    public static PublicFragment newInstance() {
        return new PublicFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("WANG", "[PublicFragment].onCreateView()");
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_public, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        publicRequesterModel.requestPublicTabList(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        tabLayoutMediator.detach();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("WANG", "[PublicFragment].onViewCreated()");
        initModel();
        initVp();
    }



    private void initModel() {
        publicStateModel = new ViewModelProvider(this).get(PublicStateModel.class);
        publicRequesterModel = new ViewModelProvider(this).get(PublicRequesterModel.class);

        publicRequesterModel.publicTabItem.observe(getViewLifecycleOwner(), new Observer<List<PublicTabDataItem>>() {
            @Override
            public void onChanged(List<PublicTabDataItem> publicDataItems) {
                Log.w("WANG", TAG + " initModel() publicTabItem onChanged. " + publicDataItems.size());
                refreshVPFragment(publicDataItems);
                tabLayoutMediator = new TabLayoutMediator(mBinding.publicTab, mBinding.publicVp2, new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(publicDataItems.get(position).getName());
                    }
                });
                tabLayoutMediator.attach();
            }
        });

    }

    private void initVp() {
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
        }
        publicViewPagerAdapter = new CommonVPAdapter(this, fragmentList);
        mBinding.publicVp2.setAdapter(publicViewPagerAdapter);
        mBinding.publicVp2.setSaveEnabled(false);
    }

    private void refreshVPFragment(List<PublicTabDataItem> tabs) {
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
        }
        fragmentList.clear();
        for (PublicTabDataItem tab : tabs) {
            fragmentList.add(PublicItemFragment.newInstance(tab.getId()));
        }
        publicViewPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public static class PublicStateModel extends StateModel {

    }

}