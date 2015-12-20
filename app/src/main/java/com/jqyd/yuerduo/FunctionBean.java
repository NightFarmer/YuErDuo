package com.jqyd.yuerduo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangfan on 2015/12/18.
 */
public class FunctionBean implements Serializable {
    public String title;
    public int icon;

    public List<FunctionBean> children = new ArrayList<>();
}
