package cn.wx.mywanandroid.data.bean.home;

public class HomeDataBean {

    private BannerData bannerData;
    private ArticleData.DatasItem articleData;

    public BannerData getBannerData() {
        return bannerData;
    }

    public void setBannerDatas(BannerData bannerData) {
        this.bannerData = bannerData;
    }

    public ArticleData.DatasItem getArticleData() {
        return articleData;
    }

    public void setArticleData(ArticleData.DatasItem articleData) {
        this.articleData = articleData;
    }
}
