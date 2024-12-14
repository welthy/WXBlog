package cn.wx.support_base.wanandroidkit;

import android.app.Application;

import androidx.lifecycle.LifecycleOwner;

import com.hjq.http.EasyConfig;
import com.hjq.http.EasyHttp;
import com.hjq.http.config.IRequestServer;
import com.hjq.http.request.DeleteRequest;
import com.hjq.http.request.DownloadRequest;
import com.hjq.http.request.GetRequest;
import com.hjq.http.request.HeadRequest;
import com.hjq.http.request.PostRequest;
import com.hjq.http.request.PutRequest;

import cn.wx.support_base.wanandroidkit.model.RequestHandler;
import cn.wx.support_base.wanandroidkit.server.WanandroidServer;
import okhttp3.OkHttpClient;

/**
 * @author 小昕
 * 好用、好调试
 * TODO
 * 内部管理：
 * 1、多域名支持
 * 2、动态HOST
 * 3、请求缓存
 * 4、上传/下载进度提示
 * 5、JSON数据解析
 * 6、批量上传/下载
 * 7、断点续传
 * 对外功能特点：
 * 1、生命周期管理
 * 2、极速上传/下载。文件的MD5值和服务器比对，如果找到了，就直接返回
 * 3、代码定位
 * 4、延迟请求
 */
public class WXHttp {

    public static void init(Application app) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        IRequestServer requestServer = new WanandroidServer();
        EasyConfig.with(okHttpClient)
                // 是否打印日志
                .setLogEnabled(false)
                // 设置服务器配置（必须设置）
                .setServer(requestServer)
                // 设置请求处理策略（必须设置）
                .setHandler(new RequestHandler(app))
                // 设置请求重试次数
                .setRetryCount(3)
                // 添加全局请求参数
                //.addParam("token", "6666666")
                // 添加全局请求头
                //.addHeader("time", "20191030")
                // 启用配置
                .into();
    }

    public static GetRequest get(LifecycleOwner lifecycleOwner) {
        return EasyHttp.get(lifecycleOwner);
    }

    public static PostRequest post(LifecycleOwner lifecycleOwner) {
        return EasyHttp.post(lifecycleOwner);
    }

    public static HeadRequest head(LifecycleOwner l) {
        return EasyHttp.head(l);
    }

    public static PutRequest put(LifecycleOwner l) {
        return EasyHttp.put(l);
    }

    public static DeleteRequest delete(LifecycleOwner l) {
        return EasyHttp.delete(l);
    }

    public static DownloadRequest download(LifecycleOwner l) {
        return EasyHttp.download(l);
    }
}
