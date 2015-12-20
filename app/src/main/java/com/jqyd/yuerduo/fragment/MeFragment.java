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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.jqyd.yuerduo.MyRecyclerViewAdapter;
import com.jqyd.yuerduo.R;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhangfan on 2015/12/14.
 */
public class MeFragment extends BaseFragment {
    @Override
    public String getTitle() {
        return "我";
    }

    @Override
    public int getIconDefault() {
        return R.drawable.more0;
    }

    @Override
    public int getIconSelected() {
        return R.drawable.more1;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this, inflate);



        return inflate;
    }
}
