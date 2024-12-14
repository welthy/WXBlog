package cn.wx.mywanandroid.data.bean.system;

import java.util.List;

public class SystemDataItem {
	private String lisense;
	private int visible;
	private String author;
	private String lisenseLink;
	private int type;
	private int parentChapterId;
	private String cover;
	private List<Object> articleList;
	private List<SystemChildrenItem> children;
	private String name;
	private boolean userControlSetTop;
	private int id;
	private int courseId;
	private String desc;
	private int order;

	public String getLisense(){
		return lisense;
	}

	public int getVisible(){
		return visible;
	}

	public String getAuthor(){
		return author;
	}

	public String getLisenseLink(){
		return lisenseLink;
	}

	public int getType(){
		return type;
	}

	public int getParentChapterId(){
		return parentChapterId;
	}

	public String getCover(){
		return cover;
	}

	public List<Object> getArticleList(){
		return articleList;
	}

	public List<SystemChildrenItem> getChildren(){
		return children;
	}

	public String getName(){
		return name;
	}

	public boolean isUserControlSetTop(){
		return userControlSetTop;
	}

	public int getId(){
		return id;
	}

	public int getCourseId(){
		return courseId;
	}

	public String getDesc(){
		return desc;
	}

	public int getOrder(){
		return order;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"lisense = '" + lisense + '\'' + 
			",visible = '" + visible + '\'' + 
			",author = '" + author + '\'' + 
			",lisenseLink = '" + lisenseLink + '\'' + 
			",type = '" + type + '\'' + 
			",parentChapterId = '" + parentChapterId + '\'' + 
			",cover = '" + cover + '\'' + 
			",articleList = '" + articleList + '\'' + 
			",children = '" + children + '\'' + 
			",name = '" + name + '\'' + 
			",userControlSetTop = '" + userControlSetTop + '\'' + 
			",id = '" + id + '\'' + 
			",courseId = '" + courseId + '\'' + 
			",desc = '" + desc + '\'' + 
			",order = '" + order + '\'' + 
			"}";
		}
}