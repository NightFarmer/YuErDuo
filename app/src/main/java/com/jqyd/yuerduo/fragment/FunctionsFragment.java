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

import com.jqyd.yuerduo.R;
import com.jqyd.yuerduo.adapter.FunctionPageAdapter;
import com.jqyd.yuerduo.bean.FunctionBean;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

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
        functionBean.icon = R.drawable.function16;
        functionBean.title = "个人办公";
        adapter.dataList.add(functionBean);

        List<FunctionBean> children = new ArrayList<>();
        FunctionBean functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function3;
        functionBean1.title = "消息通知";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function5;
        functionBean1.title = "我的订单";
        children.add(functionBean1);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function10;
        functionBean1.title = "我的客户";
        children.add(functionBean1);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function11;
        functionBean1.title = "我的应收款";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function16;
        functionBean1.title = "我的收款单";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function12;
        functionBean1.title = "我的送货单";
        children.add(functionBean1);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function19;
        functionBean1.title = "我的移动库存";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function8;
        functionBean1.title = "我的信息反馈";
        children.add(functionBean1);
        functionBean.children = children;


        functionBean = new FunctionBean();
        functionBean.icon = R.drawable.function10;
        functionBean.title = "协同办公";
        adapter.dataList.add(functionBean);

        children = new ArrayList<>();
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function3;
        functionBean1.title = "公告通知";
        children.add(functionBean1);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function5;
        functionBean1.title = "信息反馈";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function10;
        functionBean1.title = "系统信息";
        children.add(functionBean1);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function11;
        functionBean1.title = "供货商信息";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function16;
        functionBean1.title = "渠道反馈信息";
        children.add(functionBean1);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function12;
        functionBean1.title = "职员反馈信息";
        children.add(functionBean1);
        functionBean.children = children;


        functionBean = new FunctionBean();
        functionBean.icon = R.drawable.function8;
        functionBean.title = "销售管理";
        adapter.dataList.add(functionBean);

        children = new ArrayList<>();
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function3;
        functionBean1.title = "销售订单管理";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function5;
        functionBean1.title = "销售订单统计";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function10;
        functionBean1.title = "销售商品统计";
        children.add(functionBean1);
        functionBean.children = children;


        functionBean = new FunctionBean();
        functionBean.icon = R.drawable.function1;
        functionBean.title = "采购管理";
        adapter.dataList.add(functionBean);

        children = new ArrayList<>();
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function3;
        functionBean1.title = "采购订单管理";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function5;
        functionBean1.title = "采购订单统计";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function10;
        functionBean1.title = "采购商品统计";
        children.add(functionBean1);
        functionBean.children = children;


        functionBean = new FunctionBean();
        functionBean.icon = R.drawable.function5;
        functionBean.title = "财务管理";
        adapter.dataList.add(functionBean);

        children = new ArrayList<>();
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function3;
        functionBean1.title = "收款管理";
        children.add(functionBean1);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function5;
        functionBean1.title = "付款管理";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function10;
        functionBean1.title = "到账确认";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function11;
        functionBean1.title = "应缴款管理";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function16;
        functionBean1.title = "应收款查询";
        children.add(functionBean1);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function12;
        functionBean1.title = "应付款查询";
        children.add(functionBean1);
        functionBean.children = children;


        functionBean = new FunctionBean();
        functionBean.icon = R.drawable.function12;
        functionBean.title = "库存管理";
        adapter.dataList.add(functionBean);

        children = new ArrayList<>();
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function3;
        functionBean1.title = "提货管理";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function5;
        functionBean1.title = "发货管理";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function10;
        functionBean1.title = "退货管理";
        children.add(functionBean1);
        children.add(null);
        functionBean1 = new FunctionBean();
        functionBean1.icon = R.drawable.function11;
        functionBean1.title = "移动库存查询";
        children.add(functionBean1);
        functionBean.children = children;


        return inflate;
    }
}
