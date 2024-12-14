package cn.wx.mywanandroid.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;

import java.util.List;

import cn.wx.mywanandroid.R;
import cn.wx.mywanandroid.data.bean.home.BannerData;
import cn.wx.mywanandroid.data.bean.home.BannerDataItem;
import cn.wx.mywanandroid.data.bean.navigation.NavigationTabDataItem;
import cn.wx.mywanandroid.ui.home.viewholders.BannerViewHolder;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.SimpleTabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;

public class BindingAdapterUtil {

    @BindingAdapter("bannerData")
    public static void setBannerData(MZBannerView<BannerDataItem> bannerView, BannerData bannerData) {
        if (bannerData == null || bannerData.getData() == null) {
            return;
        }
        List<BannerDataItem> bannerDataItems = bannerData.getData();
        bannerView.setPages(bannerDataItems, (MZHolderCreator<BannerViewHolder>) BannerViewHolder::new);
        bannerView.start();
    }

    @BindingAdapter("isCollect")
    public static void setIsCollect(ImageView imageView, boolean isCollect) {
        if (isCollect) {
            imageView.setImageResource(R.mipmap.ic_collect_yes);
        } else {
            imageView.setImageResource(R.mipmap.ic_collect_no);
        }
    }

    @BindingAdapter("navigationTabData")
    public static void navigationTabData(VerticalTabLayout tabLayout, List<NavigationTabDataItem> datas) {

        if (datas == null || datas.size() == 0) {
            return;
        }
        tabLayout.setTabAdapter(new SimpleTabAdapter() {
            @Override
            public int getCount() {
                return datas.size();
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new ITabView.TabTitle.Builder()
                        .setContent(datas.get(position).getName())
                        .build();
            }
        });
    }



}
