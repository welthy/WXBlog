package cn.wx.mywanandroid.ui.home.viewholders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import cn.wx.mywanandroid.R;
import cn.wx.mywanandroid.data.bean.home.BannerDataItem;

public class BannerViewHolder implements MZViewHolder<BannerDataItem> {

    private ImageView mImg;
    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_banner_item_layout, null);
        mImg = view.findViewById(R.id.banner_item_img);
        return view;
    }

    @Override
    public void onBind(Context context, int i, BannerDataItem bannerData) {
        Glide.with(context).load(bannerData.getImagePath()).into(mImg);
    }
}
