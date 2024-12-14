package cn.wx.support_base.thread;

import android.annotation.SuppressLint;
import android.os.Looper;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

/**
 * @author 小昕
 * 并发执行工具
 * 提供：
 * 1、异步执行任务
 * 2、异步延迟执行任务
 * 3、异步定时执行任务
 * 4、异步循环执行延迟任务
 */
public class WXThreadUtil {

    public static void execute(Runnable r) {
        WThreadPool.execute(r);
    }

    public static void execute(Executor executor, Runnable r) {
        WThreadPool.execute(executor, r);
    }

    @SuppressLint("CheckResult")
    public static void executeDelay(Runnable r, long delay) {
        Observable.timer(delay, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                r.run();
            }
        });
    }

    public static void executeWithObserver(Observable<Object> observable, @NotNull ISubscriberCallback<Object> callback) {
        Observer<Object> observer = new Observer<Object>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                callback.onNext(o);
            }

            @Override
            public void onError(Throwable t) {
                callback.onError(t);
            }

            @Override
            public void onComplete() {
                callback.onComplete();
            }
        };
        observable.subscribe(observer);
    }


    @SuppressLint("CheckResult")
    public static void executeCircleDelay(Runnable r, long delay, long intervalCnt) {
        Observable.interval(0, delay, TimeUnit.SECONDS)
                .take(intervalCnt)
                .subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                Log.d("WANG->WXThreadUtil", "[accept] time=" + aLong);
                r.run();
            }
        });
    }
    public static void executeOnUIThread(Runnable r) {
        if (Looper.getMainLooper().isCurrentThread()) {
            r.run();
        }
     }

     public interface ISubscriberCallback <T> {
        void onNext(T t);
        void onComplete();
        void onError(Throwable throwable);
     }
}
