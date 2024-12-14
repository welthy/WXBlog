package cn.wx.mywanandroid.ui.home.adapters;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.List;

import cn.wx.mywanandroid.R;
import cn.wx.mywanandroid.base.adapter.CommonRVAdapter;
import cn.wx.mywanandroid.base.viewholder.CommonViewHolder;
import cn.wx.mywanandroid.data.bean.navigation.NavigationTabArticlesItem;
import cn.wx.mywanandroid.databinding.NavigationSubRvItemLayoutBinding;
import cn.wx.mywanandroid.ui.web.BaseWebActivity;

public class NavigationItemRVAdapter extends CommonRVAdapter<NavigationTabArticlesItem> {

    public NavigationItemRVAdapter(Context context, List<NavigationTabArticlesItem> dataList) {
        super(context, dataList);
    }

    @Override
    public int getLayoutId(int position) {
        return R.layout.navigation_sub_rv_item_layout;
    }

    @Override
    public int getVariableId() {
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder holder, int position) {
        NavigationTabArticlesItem articlesItem = getItemData(position);
        ((NavigationSubRvItemLayoutBinding)holder.mBinding).navigationArticleItemTv.setText(articlesItem.getTitle());
        holder.mBinding.getRoot().setOnClickListener(v -> {
            BaseWebActivity.startWeb(holder.mBinding.getRoot().getContext()
                    , articlesItem.getLink()
                    , articlesItem.getTitle());
        });
    }
}
