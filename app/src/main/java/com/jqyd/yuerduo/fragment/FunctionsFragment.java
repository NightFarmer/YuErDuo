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

import com.jqyd.yuerduo.MyApplication;
import com.jqyd.yuerduo.R;
import com.jqyd.yuerduo.activity.main.TopBar;
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
        return R.drawable.main_function0;
    }

    @Override
    public int getIconSelected() {
        return R.drawable.main_function1;
    }


    @Override
    public void doWithTopBar(TopBar topBar) {
        super.doWithTopBar(topBar);
        topBar.contactsRadioGroup.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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


        adapter.dataList = ((MyApplication)getActivity().getApplication()).getAllFunction();


        return inflate;
    }
}
