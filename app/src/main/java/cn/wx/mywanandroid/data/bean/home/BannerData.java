package cn.wx.mywanandroid.data.bean.home;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class BannerData {

	@SerializedName("data")
	private List<BannerDataItem> data;

	@SerializedName("errorCode")
	private int errorCode;

	@SerializedName("errorMsg")
	private String errorMsg;

	public List<BannerDataItem> getData(){
		return data;
	}

	public int getErrorCode(){
		return errorCode;
	}

	public String getErrorMsg(){
		return errorMsg;
	}

	public void setData(List<BannerDataItem> data) {
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
			"BannerDatas{" + 
			"data = '" + Arrays.toString(data.toArray()) + '\'' +
			",errorCode = '" + errorCode + '\'' + 
			",errorMsg = '" + errorMsg + '\'' + 
			"}";
		}
}