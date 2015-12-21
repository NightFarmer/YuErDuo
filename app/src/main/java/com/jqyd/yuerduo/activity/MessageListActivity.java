package com.jqyd.yuerduo.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.jqyd.yuerduo.R;
import com.jqyd.yuerduo.adapter.MessageListAdapter;
import com.jqyd.yuerduo.bean.MessageBean;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MessageListActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.topBar_title)
    TextView topBar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        ButterKnife.bind(this);

        topBar_title.setText("消息通知");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MessageListAdapter adapter = new MessageListAdapter();
        recyclerView.setAdapter(adapter);

        List<MessageBean> dataList = new ArrayList<>();

        MessageBean messageBean = new MessageBean();
        dataList.add(messageBean);
        dataList.add(messageBean);
        dataList.add(messageBean);
        dataList.add(messageBean);
        dataList.add(messageBean);
        dataList.add(messageBean);

        adapter.dataList = dataList;

        recyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(this)
                        .color(0x00000000)
                        .size(30)
                        .build());
    }


}
