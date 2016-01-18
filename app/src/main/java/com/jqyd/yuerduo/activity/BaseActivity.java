package com.jqyd.yuerduo.activity;

import android.support.v7.app.AppCompatActivity;

import com.baidu.mobstat.StatService;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by zhangfan on 2016/1/15.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        StatService.onResume(this);
        MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        StatService.onPause(this);
        MobclickAgent.onPause(this);
    }
}
