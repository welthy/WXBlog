package cn.wx.mywanandroid.ui.home.adapters;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

import cn.wx.mywanandroid.R;
import cn.wx.mywanandroid.base.adapter.CommonRVAdapter;
import cn.wx.mywanandroid.base.viewholder.CommonViewHolder;
import cn.wx.mywanandroid.data.bean.navigation.NavigationTabDataItem;
import cn.wx.mywanandroid.databinding.NavigationRvItemLayoutBinding;

public class NavigationRVAdapter extends CommonRVAdapter<NavigationTabDataItem> {

    private NavigationItemRVAdapter itemRVAdapter;

    public NavigationRVAdapter(Context context, List<NavigationTabDataItem> articleMapList) {
        super(context, articleMapList);
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder holder, int position) {
        NavigationTabDataItem tabDataItem = getItemData(position);

        ((NavigationRvItemLayoutBinding)holder.mBinding).setTabName(tabDataItem.getName());
        itemRVAdapter = new NavigationItemRVAdapter(holder.mBinding.getRoot().getContext(), tabDataItem.getArticles());
        FlexboxLayoutManager fblm = new FlexboxLayoutManager(holder.mBinding.getRoot().getContext());
        fblm.setFlexWrap(FlexWrap.WRAP);
        fblm.setFlexDirection(FlexDirection.ROW);
        ((NavigationRvItemLayoutBinding)holder.mBinding).navigationArticleRv.setLayoutManager(fblm);
        ((NavigationRvItemLayoutBinding)holder.mBinding).navigationArticleRv.setAdapter(itemRVAdapter);
    }

    @Override
    public int getLayoutId(int position) {
        return R.layout.navigation_rv_item_layout;
    }

    @Override
    public int getVariableId() {
        return 0;
    }
}
