package cn.wx.mywanandroid.data.bean.navigation;

import java.util.List;

public class NavigationTabResponse{
	private List<NavigationTabDataItem> data;
	private int errorCode;
	private String errorMsg;

	public List<NavigationTabDataItem> getData(){
		return data;
	}

	public int getErrorCode(){
		return errorCode;
	}

	public String getErrorMsg(){
		return errorMsg;
	}

	public void setData(List<NavigationTabDataItem> data) {
		this.data = data;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
 	public String toString(){
		return 
			"NavigationTabResponse{" + 
			"data = '" + data + '\'' + 
			",errorCode = '" + errorCode + '\'' + 
			",errorMsg = '" + errorMsg + '\'' + 
			"}";
		}
}