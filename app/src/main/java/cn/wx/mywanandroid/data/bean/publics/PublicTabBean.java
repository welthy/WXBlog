package cn.wx.mywanandroid.data.bean.publics;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PublicTabBean{

	@SerializedName("data")
	private List<PublicTabDataItem> data;

	@SerializedName("errorCode")
	private int errorCode;

	@SerializedName("errorMsg")
	private String errorMsg;

	public List<PublicTabDataItem> getData(){
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
			"PublicTabBean{" + 
			"data = '" + data + '\'' + 
			",errorCode = '" + errorCode + '\'' + 
			",errorMsg = '" + errorMsg + '\'' + 
			"}";
		}
}