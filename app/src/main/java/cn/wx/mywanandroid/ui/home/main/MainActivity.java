package cn.wx.mywanandroid.ui.home.main;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.wx.mywanandroid.R;
import cn.wx.mywanandroid.base.adapter.CommonVPAdapter;
import cn.wx.mywanandroid.databinding.ActivityMainBinding;
import cn.wx.mywanandroid.ui.home.fragments.HomeFragment;
import cn.wx.mywanandroid.ui.home.fragments.NavigationFragment;
import cn.wx.mywanandroid.ui.home.fragments.ProjectFragment;
import cn.wx.mywanandroid.ui.home.fragments.PublicFragment;
import cn.wx.mywanandroid.ui.home.fragments.SystemFragment;
import cn.wx.mywanandroid.utils.WindowUtil;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    private List<String> mTitles = new ArrayList<>();
    private List<Integer> mSelectIcons = new ArrayList<>();
    private List<Integer> mUnSelectIcons = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private CommonVPAdapter vpAdapter;
    private String[] tabTitles;
    private MainViewModel mainViewModel;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowUtil.transparentStatusBar(getWindow(), getResources().getColor(R.color.color_blue));
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initViewModel();
        initToolbar();

        initTab();
        initFragment();
    }

    private void initToolbar() {
        setSupportActionBar(mainBinding.mainToolbar);
        mainViewModel.title.postValue(getResources().getString(R.string.home_title));
    }

    public void updateTitle(String title) {
        mainBinding.mainToolbar.setTitle(title);
    }

    private void initViewModel() {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.title.observe(this, this::updateTitle);
    }

    private void initTab() {
        tabTitles = getResources().getStringArray(R.array.home_Tab_titles);
        Collections.addAll(mTitles, tabTitles);
        mSelectIcons.add(R.drawable.ic_home_black_24dp);
        mSelectIcons.add(R.mipmap.ic_wechat);
        mSelectIcons.add(R.mipmap.ic_system);
        mSelectIcons.add(R.mipmap.ic_navigation);
        mSelectIcons.add(R.mipmap.ic_project);

        for (int i=0; i<mTitles.size(); i++) {
            HomeTabEntity tabEntity = new HomeTabEntity(mTitles.get(i), mSelectIcons.get(i), 0);
            this.mTabEntities.add(tabEntity);
        }
        mainBinding.tabRoot.setTabData(this.mTabEntities);
        mainBinding.tabRoot.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                Log.d("WANG", "Main [onTabSelect] pos="+position);
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_fragment_container, mFragments.get(position));
                ft.commit();
                mainViewModel.title.postValue(tabTitles[position]);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void initFragment() {
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(PublicFragment.newInstance());
        mFragments.add(SystemFragment.newInstance());
        mFragments.add(NavigationFragment.newInstance());
        mFragments.add(ProjectFragment.newInstance());

        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_fragment_container, HomeFragment.newInstance());
        ft.commit();
    }

    static class HomeTabEntity implements CustomTabEntity {

        private String mTitles;
        private int mSelectIcons;
        private int mUnSelectIcons;

        public HomeTabEntity(String mTitles, int mSelectIcons, int mUnSelectIcons) {
            this.mTitles = mTitles;
            this.mSelectIcons = mSelectIcons;
            this.mUnSelectIcons = mUnSelectIcons;
        }

        @Override
        public String getTabTitle() {
            return this.mTitles;
        }

        @Override
        public int getTabSelectedIcon() {
            return this.mSelectIcons;
        }

        @Override
        public int getTabUnselectedIcon() {
            return this.mSelectIcons;
        }
    }
}