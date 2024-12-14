package cn.wx.mywanandroid.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.wx.mywanandroid.base.viewholder.CommonViewHolder;

public abstract class CommonRVAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {

    public Context context;
    public List<T> dataList;

    public CommonRVAdapter(Context context, List<T> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
        return new CommonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder holder, int position) {
        T data = dataList.get(position);
        holder.mBinding.setVariable(getVariableId(), data);
        holder.mBinding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return this.dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutId(position);
    }

    public T getItemData(int position) {
        return dataList.get(position);
    }


    public abstract int getLayoutId(int position);
    public abstract int getVariableId();

}
