package cn.wx.mywanandroid.data.bean.home;


import com.google.gson.annotations.SerializedName;

public class BannerDataItem {

	@SerializedName("imagePath")
	private String imagePath;

	@SerializedName("id")
	private int id;

	@SerializedName("isVisible")
	private int isVisible;

	@SerializedName("title")
	private String title;

	@SerializedName("type")
	private int type;

	@SerializedName("url")
	private String url;

	@SerializedName("desc")
	private String desc;

	@SerializedName("order")
	private int order;

	public String getImagePath(){
		return imagePath;
	}

	public int getId(){
		return id;
	}

	public int getIsVisible(){
		return isVisible;
	}

	public String getTitle(){
		return title;
	}

	public int getType(){
		return type;
	}

	public String getUrl(){
		return url;
	}

	public String getDesc(){
		return desc;
	}

	public int getOrder(){
		return order;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIsVisible(int isVisible) {
		this.isVisible = isVisible;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setUrl(String url) {
		this.url = url;
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
			"imagePath = '" + imagePath + '\'' + 
			",id = '" + id + '\'' + 
			",isVisible = '" + isVisible + '\'' + 
			",title = '" + title + '\'' + 
			",type = '" + type + '\'' + 
			",url = '" + url + '\'' + 
			",desc = '" + desc + '\'' + 
			",order = '" + order + '\'' + 
			"}";
		}
}