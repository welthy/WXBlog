package cn.wx.mywanandroid.ui.home.adapters;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.wx.mywanandroid.BR;
import cn.wx.mywanandroid.R;
import cn.wx.mywanandroid.base.adapter.CommonRVAdapter;
import cn.wx.mywanandroid.base.viewholder.CommonViewHolder;
import cn.wx.mywanandroid.data.bean.project.ProjectArticleDataItem;
import cn.wx.mywanandroid.databinding.ProjectRvItemLayoutBinding;
import cn.wx.mywanandroid.ui.web.BaseWebActivity;

public class ProjectRVAdapter extends CommonRVAdapter<ProjectArticleDataItem> {

    public ProjectRVAdapter(List<ProjectArticleDataItem> articleDataItemList, Context context) {
        super(context, articleDataItemList);
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ProjectArticleDataItem dataItem = getItemData(position);
        if (dataItem.isCollect()) {
            ((ProjectRvItemLayoutBinding)holder.mBinding).projectArticleItemCollect.setImageResource(R.mipmap.ic_collect_yes);
        } else {
            ((ProjectRvItemLayoutBinding)holder.mBinding).projectArticleItemCollect.setImageResource(R.mipmap.ic_collect_no);
        }
        Glide.with(context).load(dataItem.getEnvelopePic()).into(((ProjectRvItemLayoutBinding)holder.mBinding).projectArticleItemImg);
        holder.mBinding.getRoot().setOnClickListener(v -> {
            BaseWebActivity.startWeb(holder.mBinding.getRoot().getContext()
                    , dataItem.getLink()
                    , dataItem.getTitle());
        });
    }

    @Override
    public int getLayoutId(int position) {
        return R.layout.project_rv_item_layout;
    }

    @Override
    public int getVariableId() {
        return BR.projectArticleItem;
    }

}
