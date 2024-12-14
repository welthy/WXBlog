package cn.wx.mywanandroid.data.bean.project;

public class ProjectArticlesResponse{
	private ProjectArticleData data;
	private int errorCode;
	private String errorMsg;

	public ProjectArticleData getData(){
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
			"ProjectArticlesResponse{" + 
			"data = '" + data + '\'' + 
			",errorCode = '" + errorCode + '\'' + 
			",errorMsg = '" + errorMsg + '\'' + 
			"}";
		}
}
