package com.jqyd.yuerduo.widget;

/**
 * Created by zhangfan on 2015/12/21.
 */
public interface ItemTouchHelperAdapter {

    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
