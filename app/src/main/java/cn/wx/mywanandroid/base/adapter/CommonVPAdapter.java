package cn.wx.mywanandroid.base.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class CommonVPAdapter extends FragmentStateAdapter {

    private List<Fragment> mFragmentList;

    public CommonVPAdapter(@NonNull Fragment fragment, List<Fragment> mFragmentList) {
        super(fragment);
        this.mFragmentList = mFragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragmentList.size();
    }


}
