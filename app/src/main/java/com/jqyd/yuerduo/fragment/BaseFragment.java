package com.jqyd.yuerduo.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by zhangfan on 2015/12/14.
 */
public abstract class BaseFragment extends Fragment {

    public abstract String getTitle();

    public abstract int getIconDefault();
    public abstract int getIconSelected();
}
