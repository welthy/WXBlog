package cn.wx.mywanandroid.ui.home.adapters;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

import cn.wx.mywanandroid.BR;
import cn.wx.mywanandroid.R;
import cn.wx.mywanandroid.base.adapter.CommonRVAdapter;
import cn.wx.mywanandroid.base.viewholder.CommonViewHolder;
import cn.wx.mywanandroid.data.bean.system.SystemChildrenItem;
import cn.wx.mywanandroid.data.bean.system.SystemDataItem;
import cn.wx.mywanandroid.databinding.SystemRvItemLayoutBinding;

public class SystemRVAdapter extends CommonRVAdapter<SystemDataItem> {

    private SystemItemRvAdapter itemRvAdapter;

    public SystemRVAdapter(Context context, List<SystemDataItem> datas) {
        super(context, datas);
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        initRV(holder, getItemData(position));
    }

    private void initRV(CommonViewHolder holder, SystemDataItem systemDataItem) {
        if (systemDataItem == null) {
            return;
        }
        List<SystemChildrenItem> childrenItems = systemDataItem.getChildren();
        itemRvAdapter = new SystemItemRvAdapter(context, childrenItems);
        ((SystemRvItemLayoutBinding)holder.mBinding).itemRv.setAdapter(itemRvAdapter);

        FlexboxLayoutManager fblm = new FlexboxLayoutManager(context);
        fblm.setFlexWrap(FlexWrap.WRAP);
        fblm.setFlexDirection(FlexDirection.ROW);
        ((SystemRvItemLayoutBinding)holder.mBinding).itemRv.setLayoutManager(fblm);
        ((SystemRvItemLayoutBinding)holder.mBinding).itemRv.setAdapter(itemRvAdapter);
    }

    @Override
    public int getLayoutId(int position) {
        return R.layout.system_rv_item_layout;
    }

    @Override
    public int getVariableId() {
        return BR.systemDataItem;
    }
}
