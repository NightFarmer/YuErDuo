package com.jqyd.yuerduo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jqyd.yuerduo.FunctionBean;
import com.jqyd.yuerduo.FunctionPageAdapter;
import com.jqyd.yuerduo.R;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangfan on 2015/12/14.
 */
public class FunctionsFragment extends BaseFragment {
    @Override
    public String getTitle() {
        return "功能";
    }

    @Override
    public int getIconDefault() {
        return R.drawable.gongzuotai0;
    }

    @Override
    public int getIconSelected() {
        return R.drawable.gongzuotai1;
    }

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_functions, container, false);
        ButterKnife.bind(this, inflate);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FunctionPageAdapter adapter = new FunctionPageAdapter();
        recyclerView.setAdapter(adapter);


        FunctionBean functionBean = new FunctionBean();
        functionBean.title = "个人办公";
        adapter.dataList.add(functionBean);
        functionBean = new FunctionBean();
        functionBean.title = "协同办公";
        adapter.dataList.add(functionBean);
        functionBean = new FunctionBean();
        functionBean.title = "销售管理";
        adapter.dataList.add(functionBean);
        functionBean = new FunctionBean();
        functionBean.title = "采购管理";
        adapter.dataList.add(functionBean);
        functionBean = new FunctionBean();
        functionBean.title = "财务管理";
        adapter.dataList.add(functionBean);
        functionBean = new FunctionBean();
        functionBean.title = "库存管理";
        adapter.dataList.add(functionBean);


        return inflate;
    }
}
