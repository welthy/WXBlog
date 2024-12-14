package cn.wx.mywanandroid.data.bean.navigation;

import java.util.List;

/**
 * tab数据
 */
public class NavigationTabDataItem {
	private String name;
	private List<NavigationTabArticlesItem> articles;
	private int cid;

	public String getName(){
		return name;
	}

	public List<NavigationTabArticlesItem> getArticles(){
		return articles;
	}

	public int getCid(){
		return cid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setArticles(List<NavigationTabArticlesItem> articles) {
		this.articles = articles;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"name = '" + name + '\'' + 
			",articles = '" + articles + '\'' + 
			",cid = '" + cid + '\'' + 
			"}";
		}
}