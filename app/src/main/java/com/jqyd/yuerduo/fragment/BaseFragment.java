package com.jqyd.yuerduo.fragment;

import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;

import com.jqyd.yuerduo.activity.main.TopBar;

/**
 * Created by zhangfan on 2015/12/14.
 */
public abstract class BaseFragment extends Fragment {

    public abstract String getTitle();

    public abstract int getIconDefault();

    public abstract int getIconSelected();

    public void doWithTopBar(TopBar topBar){
        topBar.topBar_title.setText(getTitle());
    };

}
