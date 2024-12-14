package cn.wx.mywanandroid.data.bean.system;

import java.util.List;

public class SystemDataResponse{
	private List<SystemDataItem> data;
	private int errorCode;
	private String errorMsg;

	public List<SystemDataItem> getData(){
		return data;
	}

	public int getErrorCode(){
		return errorCode;
	}

	public String getErrorMsg(){
		return errorMsg;
	}

	@Override
 	public String toString(){
		return 
			"SystemDataResponse{" + 
			"data = '" + data + '\'' + 
			",errorCode = '" + errorCode + '\'' + 
			",errorMsg = '" + errorMsg + '\'' + 
			"}";
		}
}