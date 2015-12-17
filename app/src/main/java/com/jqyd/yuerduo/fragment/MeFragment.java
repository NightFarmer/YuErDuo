package com.jqyd.yuerduo.fragment;

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

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.myListView)
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this, inflate);

        recyclerView.setAdapter(new MyRecyclerViewAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return new Button(parent.getContext());
            }
        });

        return inflate;
    }
}
