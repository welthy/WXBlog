package cn.wx.mywanandroid.data.bean.publics;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PublicTabArticleResponse {

	@SerializedName("over")
	private boolean over;

	@SerializedName("pageCount")
	private int pageCount;

	@SerializedName("total")
	private int total;

	@SerializedName("curPage")
	private int curPage;

	@SerializedName("offset")
	private int offset;

	@SerializedName("size")
	private int size;

	@SerializedName("datas")
	private List<PublicTabArticleDataItem> datas;

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

	public List<PublicTabArticleDataItem> getDatas(){
		return datas;
	}

	public void setOver(boolean over) {
		this.over = over;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setDatas(List<PublicTabArticleDataItem> datas) {
		this.datas = datas;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"over = '" + over + '\'' + 
			",pageCount = '" + pageCount + '\'' + 
			",total = '" + total + '\'' + 
			",curPage = '" + curPage + '\'' + 
			",offset = '" + offset + '\'' + 
			",size = '" + size + '\'' + 
			",datas = '" + datas + '\'' + 
			"}";
		}
}