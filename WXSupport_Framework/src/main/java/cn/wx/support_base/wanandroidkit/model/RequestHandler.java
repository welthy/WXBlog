package cn.wx.support_base.wanandroidkit.model;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.JsonSyntaxException;
import com.hjq.gson.factory.GsonFactory;
import com.hjq.http.EasyLog;
import com.hjq.http.config.IRequestHandler;
import com.hjq.http.exception.CancelException;
import com.hjq.http.exception.DataException;
import com.hjq.http.exception.FileMd5Exception;
import com.hjq.http.exception.HttpException;
import com.hjq.http.exception.NetworkException;
import com.hjq.http.exception.NullBodyException;
import com.hjq.http.exception.ResponseException;
import com.hjq.http.exception.ServerException;
import com.hjq.http.exception.TimeoutException;
import com.hjq.http.request.HttpRequest;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import cn.wx.support_base.wanandroidkit.exception.ResultException;
import cn.wx.support_base.wanandroidkit.exception.TokenException;
import cn.wx.wxsupport_framework.R;
import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 *    @author : 小昕
 *    desc   : 请求处理类
 */
public final class RequestHandler implements IRequestHandler {

    private final Application mApplication;

    public RequestHandler(Application application) {
        mApplication = application;
    }

    @NonNull
    @Override
    public Object requestSuccess(@NonNull HttpRequest<?> httpRequest, @NonNull Response response,
                                 @NonNull Type type) throws Exception {
        if (Response.class.equals(type)) {
            return response;
        }

        if (!response.isSuccessful()) {
            throw new ResponseException(String.format(mApplication.getString(R.string.http_response_error),
                    response.code(), response.message()), response);
        }

        if (Headers.class.equals(type)) {
            return response.headers();
        }

        ResponseBody body = response.body();
        if (body == null) {
            throw new NullBodyException(mApplication.getString(R.string.http_response_null_body));
        }

        if (ResponseBody.class.equals(type)) {
            return body;
        }

        // 如果是用数组接收，判断一下是不是用 byte[] 类型进行接收的
        if(type instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            if (byte.class.equals(genericComponentType)) {
                return body.bytes();
            }
        }

        if (InputStream.class.equals(type)) {
            return body.byteStream();
        }

        if (Bitmap.class.equals(type)) {
            return BitmapFactory.decodeStream(body.byteStream());
        }

        String text;
        try {
            text = body.string();
        } catch (IOException e) {
            // 返回结果读取异常
            throw new DataException(mApplication.getString(R.string.http_data_explain_error), e);
        }

        // 打印这个 Json 或者文本
        EasyLog.printJson(httpRequest, text);

        if (String.class.equals(type)) {
            return text;
        }

        final Object result;

        try {
            result = GsonFactory.getSingletonGson().fromJson(text, type);
        } catch (JsonSyntaxException e) {
            // 返回结果读取异常
            throw new DataException(mApplication.getString(R.string.http_data_explain_error), e);
        }

        if (result instanceof HttpData) {
            HttpData<?> model = (HttpData<?>) result;
            model.setResponseHeaders(response.headers());

            if (model.isRequestSuccess()) {
                // 代表执行成功
                return result;
            }

            if (model.isTokenInvalidation()) {
                // 代表登录失效，需要重新登录
                throw new TokenException(mApplication.getString(R.string.http_token_error));
            }

            // 代表执行失败
            throw new ResultException(model.getMessage(), model);
        }
        return result;
    }

    @NonNull
    @Override
    public Exception requestFail(@NonNull HttpRequest<?> httpRequest, @NonNull Exception e) {
        if (e instanceof HttpException) {
            if (e instanceof TokenException) {
                // 登录信息失效，跳转到登录页

            }
            return e;
        }

        if (e instanceof SocketTimeoutException) {
            return new TimeoutException(mApplication.getString(R.string.http_server_out_time), e);
        }

        if (e instanceof UnknownHostException) {
            NetworkInfo info = ((ConnectivityManager) mApplication.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            // 判断网络是否连接
            if (info != null && info.isConnected()) {
                // 有连接就是服务器的问题
                return new ServerException(mApplication.getString(R.string.http_server_error), e);
            }
            // 没有连接就是网络异常
            return new NetworkException(mApplication.getString(R.string.http_network_error), e);
        }

        if (e instanceof IOException) {
            // 出现该异常的两种情况
            // 1. 调用 EasyHttp.cancel
            // 2. 网络请求被中断
            return new CancelException(mApplication.getString(R.string.http_request_cancel), e);
        }

        return new HttpException(e.getMessage(), e);
    }

    @NonNull
    @Override
    public Exception downloadFail(@NonNull HttpRequest<?> httpRequest, @NonNull Exception e) {
        if (e instanceof ResponseException) {
            ResponseException responseException = ((ResponseException) e);
            Response response = responseException.getResponse();
            responseException.setMessage(String.format(mApplication.getResources().getString(R.string.http_response_error),
                    response.code(), response.message()));
            return responseException;
        } else if (e instanceof NullBodyException) {
            NullBodyException nullBodyException = ((NullBodyException) e);
            nullBodyException.setMessage(mApplication.getString(R.string.http_response_null_body));
            return nullBodyException;
        } else if (e instanceof FileMd5Exception) {
            FileMd5Exception fileMd5Exception = ((FileMd5Exception) e);
            fileMd5Exception.setMessage(mApplication.getString(R.string.http_response_md5_error));
            return fileMd5Exception;
        }
        return requestFail(httpRequest, e);
    }

    @Nullable
    @Override
    public Object readCache(@NonNull HttpRequest<?> httpRequest, @NonNull Type type, long cacheTime) {
        String cacheKey = HttpCacheManager.generateCacheKey(httpRequest);
        String cacheValue = HttpCacheManager.readHttpCache(cacheKey);
        if (cacheValue == null || "".equals(cacheValue) || "{}".equals(cacheValue)) {
            return null;
        }
        EasyLog.printLog(httpRequest, "----- read cache key -----");
        EasyLog.printJson(httpRequest, cacheKey);
        EasyLog.printLog(httpRequest, "----- read cache value -----");
        EasyLog.printJson(httpRequest, cacheValue);
        EasyLog.printLog(httpRequest, "cacheTime = " + cacheTime);
        boolean cacheInvalidate = HttpCacheManager.isCacheInvalidate(cacheKey, cacheTime);
        EasyLog.printLog(httpRequest, "cacheInvalidate = " + cacheInvalidate);
        if (cacheInvalidate) {
            // 表示缓存已经过期了，直接返回 null 给外层，表示缓存不可用
            return null;
        }
        return GsonFactory.getSingletonGson().fromJson(cacheValue, type);
    }

    @Override
    public boolean writeCache(@NonNull HttpRequest<?> httpRequest, @NonNull Response response, @NonNull Object result) {
        String cacheKey = HttpCacheManager.generateCacheKey(httpRequest);
        String cacheValue = GsonFactory.getSingletonGson().toJson(result);
        if (cacheValue == null || "".equals(cacheValue) || "{}".equals(cacheValue)) {
            return false;
        }
        EasyLog.printLog(httpRequest, "----- write cache key -----");
        EasyLog.printJson(httpRequest, cacheKey);
        EasyLog.printLog(httpRequest, "----- write cache value -----");
        EasyLog.printJson(httpRequest, cacheValue);
        boolean writeHttpCacheResult = HttpCacheManager.writeHttpCache(cacheKey, cacheValue);
        EasyLog.printLog(httpRequest, "writeHttpCacheResult = " + writeHttpCacheResult);
        boolean refreshHttpCacheTimeResult = HttpCacheManager.setHttpCacheTime(cacheKey, System.currentTimeMillis());
        EasyLog.printLog(httpRequest, "refreshHttpCacheTimeResult = " + refreshHttpCacheTimeResult);
        return writeHttpCacheResult && refreshHttpCacheTimeResult;
    }

    @Override
    public void clearCache() {
        HttpCacheManager.clearCache();
    }
}