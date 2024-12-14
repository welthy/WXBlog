package cn.wx.support_base.wanandroidkit.exception;

import com.hjq.http.exception.HttpException;

/**
 *    @author : 小昕
 *    desc   : Token 失效异常
 */
public final class TokenException extends HttpException {

    public TokenException(String message) {
        super(message);
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }
}