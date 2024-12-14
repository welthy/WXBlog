package cn.wx.mywanandroid.ui.web;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.just.agentweb.AgentWeb;

import cn.wx.mywanandroid.R;
import cn.wx.mywanandroid.databinding.ActivityBaseWebBinding;
import cn.wx.mywanandroid.utils.WindowUtil;

public class BaseWebActivity extends AppCompatActivity {

    private static final String TAG = BaseWebActivity.class.getSimpleName();
    public static String WEB_URL = "web_url";
    public static String WEB_TITLE = "web_title";
    private ActivityBaseWebBinding mBinding;
    private AgentWeb agentWeb;

    private String title;
    private String loadUrl;

    public static void startWeb(Context context, String url, String title) {
        Intent it = new Intent(context, BaseWebActivity.class);
        it.putExtra(WEB_URL, url);
        it.putExtra(WEB_TITLE, title);
        context.startActivity(it);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_base_web);

        WindowUtil.transparentStatusBar(getWindow(), getResources().getColor(R.color.color_blue));

        initToolbar();
        parseIntent();
        initWeb();

        Log.d("WANG", TAG + " [onCreate] title="+title + " ==> url="+loadUrl);
        mBinding.webToolbar.setTitle(title);
    }

    private void parseIntent() {
        Intent it = getIntent();
        loadUrl = it.getStringExtra(WEB_URL);
        title = it.getStringExtra(WEB_TITLE);
    }

    private void initToolbar() {
        setSupportActionBar(mBinding.webToolbar);
    }

    private void initWeb() {
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(mBinding.webContainer
                        , new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                                , LinearLayout.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(loadUrl);
    }

    @Override
    protected void onStop() {
        super.onStop();
        agentWeb.getWebLifeCycle().onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        agentWeb.getWebLifeCycle().onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        agentWeb.getWebLifeCycle().onResume();
    }
}