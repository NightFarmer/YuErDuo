package com.jqyd.yuerduo.widget;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.jqyd.yuerduo.adapter.MessageListAdapter;
import com.jqyd.yuerduo.util.VibratorUtil;

/**
 * Created by zhangfan on 2015/12/21.
 */
public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final RecyclerViewDraggableAdapter mAdapter;
    private final RecyclerViewLayoutType recyclerViewLayoutType;

    public SimpleItemTouchHelperCallback(RecyclerViewDraggableAdapter adapter, RecyclerViewLayoutType recyclerViewLayoutType) {
        mAdapter = adapter;
        this.recyclerViewLayoutType = recyclerViewLayoutType;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        VibratorUtil.Vibrate(mAdapter.getContext(), 100);
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
//        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        if (viewHolder.itemView.getTag() == null) return makeMovementFlags(0, 0);
        return makeMovementFlags(recyclerViewLayoutType.dragFlags, recyclerViewLayoutType.swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        if (viewHolder.itemView.getTag() == null || target.itemView.getTag() == null) return false;
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (viewHolder.itemView.getTag() != null) {
            mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
        }
    }

    public enum RecyclerViewLayoutType {
        linear(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.START | ItemTouchHelper.END),
        grid(ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, 0);

        int dragFlags;
        int swipeFlags;

        RecyclerViewLayoutType(int dragFlags, int swipeFlags) {
            this.dragFlags = dragFlags;
            this.swipeFlags = swipeFlags;
        }
    }


}