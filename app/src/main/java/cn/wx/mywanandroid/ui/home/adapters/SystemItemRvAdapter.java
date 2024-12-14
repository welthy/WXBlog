package cn.wx.mywanandroid.ui.home.adapters;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.List;

import cn.wx.mywanandroid.BR;
import cn.wx.mywanandroid.R;
import cn.wx.mywanandroid.base.adapter.CommonRVAdapter;
import cn.wx.mywanandroid.base.viewholder.CommonViewHolder;
import cn.wx.mywanandroid.data.bean.system.SystemChildrenItem;

public class SystemItemRvAdapter extends CommonRVAdapter<SystemChildrenItem> {

    public SystemItemRvAdapter(Context context, List<SystemChildrenItem> datas) {
        super(context, datas);
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        SystemChildrenItem childrenItem = dataList.get(position);
        holder.mBinding.getRoot().setOnClickListener(v -> {

        });
    }

    @Override
    public int getLayoutId(int position) {
        return R.layout.system_rv_sub_item_layout;
    }

    @Override
    public int getVariableId() {
        return BR.systemChildItem;
    }
}
