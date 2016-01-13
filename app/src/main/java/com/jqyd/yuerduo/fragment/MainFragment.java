package com.jqyd.yuerduo.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jqyd.yuerduo.R;
import com.jqyd.yuerduo.adapter.MainPageGridAdapter;
import com.jqyd.yuerduo.bean.FunctionBean;
import com.jqyd.yuerduo.widget.DividerGridItemDecoration;
import com.jqyd.yuerduo.widget.SimpleItemTouchHelperCallback;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhangfan on 2015/12/14.
 */
public class MainFragment extends BaseFragment {
    @Override
    public String getTitle() {
        return "首页";
    }

    @Override
    public int getIconDefault() {
        return R.drawable.shouye0;
    }

    @Override
    public int getIconSelected() {
        return R.drawable.shouye1;
    }

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.layout_notice)
    View layout_notice;

    MainPageGridAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, inflate);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        recyclerView.addItemDecoration(new DividerGridItemDecoration(getContext()));

        adapter = new MainPageGridAdapter(this);
        recyclerView.setAdapter(adapter);

        SimpleItemTouchHelperCallback touchHelperCallback = new SimpleItemTouchHelperCallback(adapter, SimpleItemTouchHelperCallback.RecyclerViewLayoutType.grid);
        ItemTouchHelper touchHelper = new ItemTouchHelper(touchHelperCallback);
        touchHelper.attachToRecyclerView(recyclerView);

        FunctionBean functionBean = new FunctionBean();
        functionBean.title = "消息通知";
        functionBean.icon = R.drawable.function3;
        adapter.dataList.add(functionBean);
        functionBean = new FunctionBean();
        functionBean.title = "我的订单";
        functionBean.icon = R.drawable.function5;
        adapter.dataList.add(functionBean);
        functionBean = new FunctionBean();
        functionBean.title = "提货管理";
        functionBean.icon = R.drawable.function16;
        adapter.dataList.add(functionBean);
        functionBean = new FunctionBean();
        functionBean.title = "发货管理";
        functionBean.icon = R.drawable.function8;
        adapter.dataList.add(functionBean);
        functionBean = new FunctionBean();
        functionBean.title = "我的应收款";
        functionBean.icon = R.drawable.function7;
        adapter.dataList.add(functionBean);
        functionBean = new FunctionBean();
        functionBean.title = "我的收款单";
        functionBean.icon = R.drawable.function4;
        adapter.dataList.add(functionBean);
        functionBean = new FunctionBean();
        functionBean.title = "我的送货单";
        functionBean.icon = R.drawable.function11;
        adapter.dataList.add(functionBean);
        functionBean = new FunctionBean();
        functionBean.title = "移动库存";
        functionBean.icon = R.drawable.function12;
        adapter.dataList.add(functionBean);
        functionBean = new FunctionBean();
        functionBean.title = "信息反馈";
        functionBean.icon = R.drawable.function3;
        adapter.dataList.add(functionBean);
        functionBean = new FunctionBean();
        functionBean.title = "我的客户";
        functionBean.icon = R.drawable.function10;
        adapter.dataList.add(functionBean);


        return inflate;
    }

    @OnClick(R.id.bt_notice_close)
    public void onCloseNotice() {
        layout_notice.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK){
            adapter.dataList = (ArrayList<FunctionBean>) data.getSerializableExtra("dataList");
            adapter.notifyDataSetChanged();
        }
    }
}
