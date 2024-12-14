package cn.wx.mywanandroid.data.bean.project;

import java.util.List;

public class ProjectTabResponse{
	private List<ProjectTabDataItem> data;
	private int errorCode;
	private String errorMsg;

	public List<ProjectTabDataItem> getData(){
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
			"ProjectTabResponse{" + 
			"data = '" + data + '\'' + 
			",errorCode = '" + errorCode + '\'' + 
			",errorMsg = '" + errorMsg + '\'' + 
			"}";
		}
}