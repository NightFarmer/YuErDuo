package com.jqyd.yuerduo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 常用功能新增界面
 */
public class MainFunctionAddActivity extends AppCompatActivity {

    @Bind(R.id.bt_ok)
    View bt_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_function_add);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_ok)
    public void onOk(){
        finish();
    }
}
