package com.jqyd.yuerduo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jqyd.yuerduo.R;

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
