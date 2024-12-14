package cn.wx.mywanandroid.data.bean.project;

import java.util.List;

public class ProjectArticleData {
	private boolean over;
	private int pageCount;
	private int total;
	private int curPage;
	private int offset;
	private int size;
	private List<ProjectArticleDataItem> datas;

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

	public List<ProjectArticleDataItem> getDatas(){
		return datas;
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