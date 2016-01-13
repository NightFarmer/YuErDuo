package com.jqyd.yuerduo.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.jqyd.yuerduo.R;
import com.jqyd.yuerduo.adapter.MessageListAdapter;
import com.jqyd.yuerduo.bean.MessageBean;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageListActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.topBar_title)
    TextView topBar_title;

    @Bind(R.id.yyy)
    View yyy;

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


        Button button = new Button(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MessageListActivity.this, "xxx", Toast.LENGTH_SHORT).show();
            }
        });
        popupWindow = new PopupWindow(button, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);

    }


    private PopupWindow popupWindow;

    @OnClick(R.id.xxx)
    public void xx(){
        popupWindow.showAtLocation(yyy, Gravity.BOTTOM, 0, 0);
    }

}
