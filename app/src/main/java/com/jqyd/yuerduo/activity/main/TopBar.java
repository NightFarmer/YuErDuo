package com.jqyd.yuerduo.activity.main;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jqyd.yuerduo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangfan on 16-1-8.
 */
public class TopBar {
    @Bind(R.id.topBar_back)
    public ImageButton bt_back;
    @Bind(R.id.topBar_title)
    public TextView topBar_title;
    @Bind(R.id.topBar_right_button)
    public TextView right_button;
    @Bind(R.id.topBar_right_icon)
    public ImageButton right_icon;
    @Bind(R.id.arrow_down)
    public ImageButton arrow_down;

    public TopBar(View view) {
        ButterKnife.bind(this, view);
    }
}