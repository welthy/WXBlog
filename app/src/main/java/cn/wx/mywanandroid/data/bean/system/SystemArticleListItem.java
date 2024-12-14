package cn.wx.mywanandroid.data.bean.system;

import java.util.List;

public class SystemArticleListItem {
	private long shareDate;
	private String projectLink;
	private String prefix;
	private boolean canEdit;
	private String origin;
	private String link;
	private String title;
	private int type;
	private int selfVisible;
	private String apkLink;
	private String envelopePic;
	private int audit;
	private int chapterId;
	private String host;
	private int realSuperChapterId;
	private int id;
	private int courseId;
	private String superChapterName;
	private String descMd;
	private long publishTime;
	private String niceShareDate;
	private int visible;
	private String niceDate;
	private String author;
	private int zan;
	private String chapterName;
	private int userId;
	private boolean adminAdd;
	private boolean isAdminAdd;
	private List<Object> tags;
	private int superChapterId;
	private boolean fresh;
	private boolean collect;
	private String shareUser;
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

	public List<Object> getTags(){
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

	@Override
 	public String toString(){
		return 
			"ArticleListItem{" + 
			"shareDate = '" + shareDate + '\'' + 
			",projectLink = '" + projectLink + '\'' + 
			",prefix = '" + prefix + '\'' + 
			",canEdit = '" + canEdit + '\'' + 
			",origin = '" + origin + '\'' + 
			",link = '" + link + '\'' + 
			",title = '" + title + '\'' + 
			",type = '" + type + '\'' + 
			",selfVisible = '" + selfVisible + '\'' + 
			",apkLink = '" + apkLink + '\'' + 
			",envelopePic = '" + envelopePic + '\'' + 
			",audit = '" + audit + '\'' + 
			",chapterId = '" + chapterId + '\'' + 
			",host = '" + host + '\'' + 
			",realSuperChapterId = '" + realSuperChapterId + '\'' + 
			",id = '" + id + '\'' + 
			",courseId = '" + courseId + '\'' + 
			",superChapterName = '" + superChapterName + '\'' + 
			",descMd = '" + descMd + '\'' + 
			",publishTime = '" + publishTime + '\'' + 
			",niceShareDate = '" + niceShareDate + '\'' + 
			",visible = '" + visible + '\'' + 
			",niceDate = '" + niceDate + '\'' + 
			",author = '" + author + '\'' + 
			",zan = '" + zan + '\'' + 
			",chapterName = '" + chapterName + '\'' + 
			",userId = '" + userId + '\'' + 
			",adminAdd = '" + adminAdd + '\'' + 
			",isAdminAdd = '" + isAdminAdd + '\'' + 
			",tags = '" + tags + '\'' + 
			",superChapterId = '" + superChapterId + '\'' + 
			",fresh = '" + fresh + '\'' + 
			",collect = '" + collect + '\'' + 
			",shareUser = '" + shareUser + '\'' + 
			",desc = '" + desc + '\'' + 
			"}";
		}
}