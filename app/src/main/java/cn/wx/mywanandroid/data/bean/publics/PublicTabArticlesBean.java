package cn.wx.mywanandroid.data.bean.publics;

import com.google.gson.annotations.SerializedName;

public class PublicTabArticlesBean{

	@SerializedName("data")
	private PublicTabArticleResponse data;

	@SerializedName("errorCode")
	private int errorCode;

	@SerializedName("errorMsg")
	private String errorMsg;

	public PublicTabArticleResponse getData(){
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
			"PublicTabArticlesBean{" + 
			"data = '" + data + '\'' + 
			",errorCode = '" + errorCode + '\'' + 
			",errorMsg = '" + errorMsg + '\'' + 
			"}";
		}
}