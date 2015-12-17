package com.jqyd.yuerduo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jqyd.yuerduo.R;

/**
 * Created by zhangfan on 2015/12/14.
 */
public class FunctionsFragment extends BaseFragment {
    @Override
    public String getTitle() {
        return "发现";
    }

    @Override
    public int getIconDefault() {
        return R.drawable.gongzuotai0;
    }

    @Override
    public int getIconSelected() {
        return R.drawable.gongzuotai1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_functions, container, false);
    }
}
