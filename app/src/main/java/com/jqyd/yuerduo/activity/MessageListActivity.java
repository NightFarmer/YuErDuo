package com.jqyd.yuerduo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jqyd.yuerduo.R;
import com.jqyd.yuerduo.adapter.MessageListAdapter;
import com.jqyd.yuerduo.bean.MessageBean;
import com.nightfarmer.draggablerecyclerview.DraggableRecyclerView;
import com.nightfarmer.draggablerecyclerview.ProgressStyle;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 消息通知列表
 * Created by zhangfan on 2016/1/7.
 */
public class MessageListActivity extends BaseActivity {

    @Bind(R.id.recyclerView)
    DraggableRecyclerView mRecyclerView;

    @Bind(R.id.topBar_title)
    TextView topBar_title;

    Handler handler;
    @Bind(R.id.topBar_back)
    ImageButton topBarBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        ButterKnife.bind(this);

        topBarBack.setVisibility(View.VISIBLE);

        topBar_title.setText("消息通知");
        handler = new Handler();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        MessageListAdapter adapter = new MessageListAdapter();
        mRecyclerView.setAdapter(adapter);

        List<MessageBean> dataList = new ArrayList<>();

        MessageBean messageBean = new MessageBean();
        dataList.add(messageBean);
        dataList.add(messageBean);
        dataList.add(messageBean);
        dataList.add(messageBean);
        dataList.add(messageBean);
        dataList.add(messageBean);

        adapter.dataList = dataList;

        mRecyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(this)
                        .color(getResources().getColor(R.color.borderDark))
                        .size(1)
                        .build());

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLaodingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

        mRecyclerView.setLoadingListener(new DraggableRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                mRecyclerView.refreshComplete();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerView.loadMoreComplete();
                    }
                }, 2000);
            }

            @Override
            public void onCancelRefresh() {
                handler.removeCallbacksAndMessages(null);
            }
        });

        mRecyclerView.forceRefresh();
    }


    @OnClick(R.id.topBar_back)
    public void onBack() {
        finish();
    }
}
