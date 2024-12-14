package cn.wx.support_base.thread;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 小昕
 * 线程池
 */
public class WThreadPool {

    private static final int CORE_THREAD = Runtime.getRuntime().availableProcessors() * 2 + 1;
    private static final int IDLE_THREAD = 20;

    private static final Executor mDefaultExecutor;
    private static final LinkedBlockingQueue<Runnable> mDequeue = new LinkedBlockingQueue<>();
    private static final ThreadFactory mThreadFactory;
    private static final HashMap<String, Runnable> mThreadMap = new HashMap<>();

    static {
        mThreadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                String threadName = "WThreadPool" + System.currentTimeMillis();
                thread.setName(threadName);
                thread.setPriority(Thread.NORM_PRIORITY);
                mThreadMap.put(threadName, r);
                return thread;
            }
        };

        mDefaultExecutor = new ThreadPoolExecutor(
                CORE_THREAD , IDLE_THREAD
                , 1000, TimeUnit.MILLISECONDS
                , mDequeue, mThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static void execute(Runnable runnable) {
        if (mDefaultExecutor != null) {
            mDefaultExecutor.execute(runnable);
        }
    }

    public static void execute(Executor executor, Runnable runnable) {
        if (executor != null) {
            executor.execute(runnable);
        } else {
            mDefaultExecutor.execute(runnable);
        }
    }
}
