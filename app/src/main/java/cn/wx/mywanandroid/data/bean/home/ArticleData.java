package cn.wx.mywanandroid.data.bean.home;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleData extends BaseObservable {

    @Bindable
    @SerializedName("data")
    private Data data;

    @Bindable
    @SerializedName("errorCode")
    private int errorCode;

    @Bindable
    @SerializedName("errorMsg")
    private String errorMsg;

    public Data getData(){
        return data;
    }

    public int getErrorCode(){
        return errorCode;
    }

    public String getErrorMsg(){
        return errorMsg;
    }

    public static class Data extends BaseObservable{

        @Bindable
        @SerializedName("over")
        private boolean over;

        @Bindable
        @SerializedName("pageCount")
        private int pageCount;

        @Bindable
        @SerializedName("total")
        private int total;

        @Bindable
        @SerializedName("curPage")
        private int curPage;

        @Bindable
        @SerializedName("offset")
        private int offset;

        @Bindable
        @SerializedName("size")
        private int size;

        @Bindable
        @SerializedName("datas")
        private List<DatasItem> datas;

        public boolean isOver(){
            return over;
        }

        public int getPageCount(){
            return pageCount;
        }

        public int getTotal(){
            return total;
        }

        public int getCurPage(){
            return curPage;
        }

        public int getOffset(){
            return offset;
        }

        public int getSize(){
            return size;
        }

        public List<DatasItem> getDatas(){
            return datas;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "over=" + over +
                    ", pageCount=" + pageCount +
                    ", total=" + total +
                    ", curPage=" + curPage +
                    ", offset=" + offset +
                    ", size=" + size +
                    ", datas=" + datas +
                    '}';
        }
    }

    public static class TagsItem{

        @SerializedName("name")
        public String name;

        @SerializedName("url")
        public String url;

        public String getName(){
            return name;
        }

        public String getUrl(){
            return url;
        }

        @Override
        public String toString() {
            return "TagsItem{" +
                    "name='" + name + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    public static class DatasItem {

        @SerializedName("shareDate")
        private long shareDate;

        @SerializedName("projectLink")
        private String projectLink;

        @SerializedName("prefix")
        private String prefix;

        @SerializedName("canEdit")
        private boolean canEdit;

        @SerializedName("origin")
        private String origin;

        @SerializedName("link")
        private String link;

        @SerializedName("title")
        private String title;

        @SerializedName("type")
        private int type;

        @SerializedName("selfVisible")
        private int selfVisible;

        @SerializedName("apkLink")
        private String apkLink;

        @SerializedName("envelopePic")
        private String envelopePic;

        @SerializedName("audit")
        private int audit;

        @SerializedName("chapterId")
        private int chapterId;

        @SerializedName("host")
        private String host;

        @SerializedName("realSuperChapterId")
        private int realSuperChapterId;

        @SerializedName("id")
        private int id;

        @SerializedName("courseId")
        private int courseId;

        @SerializedName("superChapterName")
        private String superChapterName;

        @SerializedName("descMd")
        private String descMd;

        @SerializedName("publishTime")
        private long publishTime;

        @SerializedName("niceShareDate")
        private String niceShareDate;

        @SerializedName("visible")
        private int visible;

        @SerializedName("niceDate")
        private String niceDate;

        @SerializedName("author")
        private String author;

        @SerializedName("zan")
        private int zan;

        @SerializedName("chapterName")
        private String chapterName;

        @SerializedName("userId")
        private int userId;

        @SerializedName("adminAdd")
        private boolean adminAdd;

        @SerializedName("isAdminAdd")
        private boolean isAdminAdd;

        @SerializedName("tags")
        private List<TagsItem> tags;

        @SerializedName("superChapterId")
        private int superChapterId;

        @SerializedName("fresh")
        private boolean fresh;

        @SerializedName("collect")
        private boolean collect;

        @SerializedName("shareUser")
        private String shareUser;

        @SerializedName("desc")
        private String desc;

        public long getShareDate(){
            return shareDate;
        }

        public String getProjectLink(){
            return projectLink;
        }

        public String getPrefix(){
            return prefix;
        }

        public boolean isCanEdit(){
            return canEdit;
        }

        public String getOrigin(){
            return origin;
        }

        public String getLink(){
            return link;
        }

        public String getTitle(){
            return title;
        }

        public int getType(){
            return type;
        }

        public int getSelfVisible(){
            return selfVisible;
        }

        public String getApkLink(){
            return apkLink;
        }

        public String getEnvelopePic(){
            return envelopePic;
        }

        public int getAudit(){
            return audit;
        }

        public int getChapterId(){
            return chapterId;
        }

        public String getHost(){
            return host;
        }

        public int getRealSuperChapterId(){
            return realSuperChapterId;
        }

        public int getId(){
            return id;
        }

        public int getCourseId(){
            return courseId;
        }

        public String getSuperChapterName(){
            return superChapterName;
        }

        public String getDescMd(){
            return descMd;
        }

        public long getPublishTime(){
            return publishTime;
        }

        public String getNiceShareDate(){
            return niceShareDate;
        }

        public int getVisible(){
            return visible;
        }

        public String getNiceDate(){
            return niceDate;
        }

        public String getAuthor(){
            return author;
        }

        public int getZan(){
            return zan;
        }

        public String getChapterName(){
            return chapterName;
        }

        public int getUserId(){
            return userId;
        }

        public boolean isAdminAdd(){
            return adminAdd;
        }

        public boolean isIsAdminAdd(){
            return isAdminAdd;
        }

        public List<TagsItem> getTags(){
            return tags;
        }

        public int getSuperChapterId(){
            return superChapterId;
        }

        public boolean isFresh(){
            return fresh;
        }

        public boolean isCollect(){
            return collect;
        }

        public String getShareUser(){
            return shareUser;
        }

        public String getDesc(){
            return desc;
        }

        public void setShareDate(long shareDate) {
            this.shareDate = shareDate;
        }

        public void setProjectLink(String projectLink) {
            this.projectLink = projectLink;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public void setCanEdit(boolean canEdit) {
            this.canEdit = canEdit;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setSelfVisible(int selfVisible) {
            this.selfVisible = selfVisible;
        }

        public void setApkLink(String apkLink) {
            this.apkLink = apkLink;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public void setAudit(int audit) {
            this.audit = audit;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public void setRealSuperChapterId(int realSuperChapterId) {
            this.realSuperChapterId = realSuperChapterId;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public void setSuperChapterName(String superChapterName) {
            this.superChapterName = superChapterName;
        }

        public void setDescMd(String descMd) {
            this.descMd = descMd;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public void setNiceShareDate(String niceShareDate) {
            this.niceShareDate = niceShareDate;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setAdminAdd(boolean adminAdd) {
            this.adminAdd = adminAdd;
        }

        public void setTags(List<TagsItem> tags) {
            this.tags = tags;
        }

        public void setSuperChapterId(int superChapterId) {
            this.superChapterId = superChapterId;
        }

        public void setFresh(boolean fresh) {
            this.fresh = fresh;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        public void setShareUser(String shareUser) {
            this.shareUser = shareUser;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "DatasItem{" +
                    "shareDate=" + shareDate +
                    ", projectLink='" + projectLink + '\'' +
                    ", prefix='" + prefix + '\'' +
                    ", canEdit=" + canEdit +
                    ", origin='" + origin + '\'' +
                    ", link='" + link + '\'' +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", selfVisible=" + selfVisible +
                    ", apkLink='" + apkLink + '\'' +
                    ", envelopePic='" + envelopePic + '\'' +
                    ", audit=" + audit +
                    ", chapterId=" + chapterId +
                    ", host='" + host + '\'' +
                    ", realSuperChapterId=" + realSuperChapterId +
                    ", id=" + id +
                    ", courseId=" + courseId +
                    ", superChapterName='" + superChapterName + '\'' +
                    ", descMd='" + descMd + '\'' +
                    ", publishTime=" + publishTime +
                    ", niceShareDate='" + niceShareDate + '\'' +
                    ", visible=" + visible +
                    ", niceDate='" + niceDate + '\'' +
                    ", author='" + author + '\'' +
                    ", zan=" + zan +
                    ", chapterName='" + chapterName + '\'' +
                    ", userId=" + userId +
                    ", adminAdd=" + adminAdd +
                    ", isAdminAdd=" + isAdminAdd +
                    ", tags=" + tags +
                    ", superChapterId=" + superChapterId +
                    ", fresh=" + fresh +
                    ", collect=" + collect +
                    ", shareUser='" + shareUser + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HomeArticleBean{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}