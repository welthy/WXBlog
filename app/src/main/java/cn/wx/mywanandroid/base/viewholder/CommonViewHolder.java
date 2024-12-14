package cn.wx.mywanandroid.base.viewholder;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class CommonViewHolder extends RecyclerView.ViewHolder {

    public ViewDataBinding mBinding;
    public CommonViewHolder(ViewDataBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }
}
