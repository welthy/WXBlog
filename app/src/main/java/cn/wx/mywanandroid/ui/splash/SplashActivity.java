package cn.wx.mywanandroid.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import cn.wx.mywanandroid.R;
import cn.wx.mywanandroid.base.ui.StateModel;
import cn.wx.mywanandroid.ui.home.main.MainActivity;
import cn.wx.support_base.log.WXLog;
import cn.wx.support_base.thread.WXThreadUtil;

public class SplashActivity extends AppCompatActivity {

    private AppCompatButton mSkipBtn;
    private volatile int count = 5;
    private volatile boolean isFinishSplash = false;
    private SplashModel splashModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WXLog.d("WANG->Splash", "[onStart]");
        setContentView(R.layout.activity_splash);

        init();
    }

    private void init() {
        initView();
        initEvent();
    }

    private void initView() {
        mSkipBtn = findViewById(R.id.countdown_btn);
    }

    private void initEvent() {
        mSkipBtn.setOnClickListener(v -> {
            //进入首页
            jumpToMain();
        });
        splashModel = new ViewModelProvider(this).get(SplashModel.class);
        splashModel._skipTime.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (count < 1) {
                    //进入首页
                    jumpToMain();
                } else {
                    runOnUiThread(() -> {
                        mSkipBtn.setText(String.format(getResources().getString(R.string.splash_btn_content), String.valueOf(integer)));
                    });
                }
            }
        });
        startCountDownJob();
    }

    private void startCountDownJob() {
        splashModel._skipTime.setValue(count);

        WXThreadUtil.executeCircleDelay(() -> {
            WXLog.d("WANG->Splash", "[startCountDownJob] count="+count);
            splashModel._skipTime.postValue(--count);
        }, 1L, 5L);
    }

    @Override
    protected void onStart() {
        super.onStart();
        WXLog.d("WANG->Splash", "[onStart]");

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void jumpToMain() {
        if (isFinishSplash) {
            return;
        }
        isFinishSplash = true;
        Intent it = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(it);
        finish();
    }

    public static class SplashModel extends StateModel {
        public MutableLiveData<Integer> _skipTime = new MutableLiveData<Integer>();
    }
}