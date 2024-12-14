package cn.wx.mywanandroid.ui.home.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.wx.mywanandroid.BR;
import cn.wx.mywanandroid.R;
import cn.wx.mywanandroid.base.viewholder.CommonViewHolder;
import cn.wx.mywanandroid.data.bean.home.ArticleData;
import cn.wx.mywanandroid.data.bean.home.HomeDataBean;
import cn.wx.mywanandroid.ui.web.BaseWebActivity;

public class HomeRVAdapter extends RecyclerView.Adapter<CommonViewHolder> {


    private List<HomeDataBean> dataList;

    public HomeRVAdapter(List<HomeDataBean> homeDataBeanList) {
        super();
        this.dataList = homeDataBeanList;
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
        return new CommonViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder holder, int position) {
        HomeDataBean homeDataBean = dataList.get(position);
        if (homeDataBean.getBannerData() != null) {
            holder.mBinding.setVariable(BR.bannerData, homeDataBean.getBannerData());
        } else {
            holder.mBinding.setVariable(BR.homeArticleData, homeDataBean.getArticleData());
            List<ArticleData.TagsItem> tags = homeDataBean.getArticleData().getTags();
            for (ArticleData.TagsItem tag : tags) {
                if ("置顶".equals(tag.name)) {
                    holder.mBinding.setVariable(BR.isTopTag, true);
                } else if ("新".equals(tag.name)) {
                    holder.mBinding.setVariable(BR.isNewTag, true);
                } else if ("公众号".equals(tag.name)) {
                    holder.mBinding.setVariable(BR.isPublicTag, true);
                } else {
                    holder.mBinding.setVariable(BR.isTopTag, false);
                    holder.mBinding.setVariable(BR.isNewTag, false);
                    holder.mBinding.setVariable(BR.isPublicTag, false);
                }
            }
            holder.mBinding.getRoot().setOnClickListener(v -> {
                BaseWebActivity.startWeb(holder.mBinding.getRoot().getContext()
                        , homeDataBean.getArticleData().getLink()
                        , homeDataBean.getArticleData().getTitle());
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        HomeDataBean homeDataBean = dataList.get(position);
        if (homeDataBean.getBannerData() != null) {
            return R.layout.home_banner_layout;
        } else {
            return R.layout.home_rv_item_layout;
        }
    }

}
