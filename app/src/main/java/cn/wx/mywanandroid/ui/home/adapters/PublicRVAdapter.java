package cn.wx.mywanandroid.ui.home.adapters;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.List;

import cn.wx.mywanandroid.BR;
import cn.wx.mywanandroid.R;
import cn.wx.mywanandroid.base.adapter.CommonRVAdapter;
import cn.wx.mywanandroid.base.viewholder.CommonViewHolder;
import cn.wx.mywanandroid.data.bean.publics.PublicTabArticleDataItem;
import cn.wx.mywanandroid.data.bean.publics.PublicTagsItem;
import cn.wx.mywanandroid.ui.web.BaseWebActivity;

public class PublicRVAdapter extends CommonRVAdapter<PublicTabArticleDataItem> {

    public PublicRVAdapter(Context context, List<PublicTabArticleDataItem> dataBeans) {
        super(context, dataBeans);
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder holder, int position) {
        PublicTabArticleDataItem tabArticleDataItem = getItemData(position);
        holder.mBinding.setVariable(BR.publicArticleData, tabArticleDataItem);
        for (PublicTagsItem tagsItem : tabArticleDataItem.getTags()) {
            if ("置顶".equals(tagsItem.getName())) {
                holder.mBinding.setVariable(BR.isTopTag, true);
            } else if ("新".equals(tagsItem.getName())) {
                holder.mBinding.setVariable(BR.isNewTag, true);
            } else if ("公众号".equals(tagsItem.getName())) {
                holder.mBinding.setVariable(BR.isPublicTag, true);
            } else {
                holder.mBinding.setVariable(BR.isTopTag, false);
                holder.mBinding.setVariable(BR.isNewTag, false);
                holder.mBinding.setVariable(BR.isPublicTag, false);
            }
        }
        holder.mBinding.getRoot().setOnClickListener(v -> {
            BaseWebActivity.startWeb(holder.mBinding.getRoot().getContext()
                    , tabArticleDataItem.getLink()
                    , tabArticleDataItem.getTitle());
        });
    }

    @Override
    public int getLayoutId(int position) {
        return R.layout.public_rv_item_layout;
    }

    @Override
    public int getVariableId() {
        return 0;
    }

}
