package com.jqyd.yuerduo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangfan on 2015/12/18.
 */
public class FunctionBean implements Serializable {
    public String title;
    public int icon;

    public boolean checked;
    public int level = 1;

    public List<FunctionBean> children = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        return this.title.equals(((FunctionBean) o).title);
    }
}
