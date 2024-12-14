package cn.wx.support_base.log;

import android.app.Application;
import android.os.Environment;

import com.tencent.mars.xlog.Log;
import com.tencent.mars.xlog.Xlog;

/**
 * @author 小昕
 * 日志工具
 * 利用XLog框架打印日志
 * TODO：
 * 1、任意数据类型的信息打印
 * 2、日志存盘
 * 3、android.Log、控制台等多种打印方式
 * 4、打印进程、线程信息
 * 5、不影响主进程（采用native打印）
 * 6、自定义日志打印格式
 * 7、日志自动备份、清理
 * 8、日志压缩
 * 9、支持json、xml格式的日志解析打印
 * 10、支持打印日志所在函数名称
 * 11、日志拦截。拦截android.util.Log打印，转为由WXLog打印
 */
public class WXLog {

    private static final String TAG = WXLog.class.getSimpleName();

    private static Xlog mLogger;


    public static void init(Application app) {
        System.loadLibrary("c++_shared");
        System.loadLibrary("marsxlog");
        final String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
        final String logPath = SDCARD + "/marssample/log";

        // this is necessary, or may crash for SIGBUS
        final String cachePath = app.getFilesDir() + "/xlog";

        //init xlog
        mLogger = new Xlog();
        Log.appenderOpen(Xlog.LEVEL_DEBUG, Xlog.AppednerModeAsync, cachePath, logPath, "WXWandroid", 0);
        Log.setLogImp(mLogger);
    }

    public static void i(String tag, Object msg) {
        Log.i(TAG, tag + "==>" + msg);
    }

    public static void d(String tag, Object msg) {
        Log.d(TAG, tag + "==>" + msg);
    }

    public static void v(String tag, Object msg) {
        Log.v(TAG, tag + "==>" + msg);
    }

    public static void w(String tag, Object msg) {
        Log.w(TAG, tag + "==>" + msg);
    }

    public static void e(String tag, Object msg) {
        Log.e(TAG, tag + "==>" + msg);
    }
}
