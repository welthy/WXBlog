package cn.wx.support_base.wanandroidkit.exception;

import androidx.annotation.NonNull;

import com.hjq.http.exception.HttpException;

import cn.wx.support_base.wanandroidkit.model.HttpData;

/**
 *    @author : 小昕
 *    desc   : 返回结果异常
 */
public final class ResultException extends HttpException {

    private final HttpData<?> mData;

    public ResultException(String message, HttpData<?> data) {
        super(message);
        mData = data;
    }

    public ResultException(String message, Throwable cause, HttpData<?> data) {
        super(message, cause);
        mData = data;
    }

    @NonNull
    public HttpData<?> getHttpData() {
        return mData;
    }
}