package com.jqyd.yuerduo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jqyd.yuerduo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PersonDetail extends BaseActivity {

    @Bind(R.id.topBar_back)
    ImageButton topBarBack;
    @Bind(R.id.topBar_title)
    TextView topBarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        ButterKnife.bind(this);

        topBarTitle.setText("个人信息");
        topBarBack.setVisibility(View.VISIBLE);
    }

}
