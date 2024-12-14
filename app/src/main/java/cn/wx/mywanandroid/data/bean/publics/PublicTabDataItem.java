package cn.wx.mywanandroid.data.bean.publics;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PublicTabDataItem {

	@SerializedName("lisense")
	private String lisense;

	@SerializedName("visible")
	private int visible;

	@SerializedName("author")
	private String author;

	@SerializedName("lisenseLink")
	private String lisenseLink;

	@SerializedName("type")
	private int type;

	@SerializedName("parentChapterId")
	private int parentChapterId;

	@SerializedName("cover")
	private String cover;

	@SerializedName("articleList")
	private List<Object> articleList;

	@SerializedName("children")
	private List<Object> children;

	@SerializedName("name")
	private String name;

	@SerializedName("userControlSetTop")
	private boolean userControlSetTop;

	@SerializedName("id")
	private int id;

	@SerializedName("courseId")
	private int courseId;

	@SerializedName("desc")
	private String desc;

	@SerializedName("order")
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

	public List<Object> getChildren(){
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

	public void setLisense(String lisense) {
		this.lisense = lisense;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setLisenseLink(String lisenseLink) {
		this.lisenseLink = lisenseLink;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setParentChapterId(int parentChapterId) {
		this.parentChapterId = parentChapterId;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public void setArticleList(List<Object> articleList) {
		this.articleList = articleList;
	}

	public void setChildren(List<Object> children) {
		this.children = children;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUserControlSetTop(boolean userControlSetTop) {
		this.userControlSetTop = userControlSetTop;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setOrder(int order) {
		this.order = order;
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