package cn.wx.support_base.preference;

import android.content.Context;
import android.util.Log;

import com.tencent.mmkv.MMKV;

/**
 * @author 小昕
 * K-V格式的数据持久化存储
 * 利用MMKV进行数据存储
 * TODO
 * 1、支持任意数据类型存储，对象可序列化（protobuf）
 * 2、native方式读写。速度快
 * 3、存储文件自定义
 * 4、可多进程读取
 */
public class PreferenceUtil {

    private static final String TAG = PreferenceUtil.class.getSimpleName();

    private static final String IS_INITED = "is_inited";

    public static void init(Context context) {
        String rootDir = MMKV.initialize(context);
        Log.d(TAG, "[init], rootDir=" + rootDir);
        MMKV.defaultMMKV().encode(IS_INITED, true);
    }

    public static MMKV get() {
        MMKV mmkv = MMKV.defaultMMKV();
        boolean isInited  = mmkv.decodeBool(IS_INITED);
        if (!isInited) {
            throw new IllegalStateException("PreferenceUtil is not init");
        }
        return mmkv;
    }

}
