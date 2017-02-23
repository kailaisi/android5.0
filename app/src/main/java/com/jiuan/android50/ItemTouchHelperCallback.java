package com.jiuan.android50;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * 拖拽回调
 * Created by wjx on 2016/12/22.
 */

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
    ItemTouchHelperAdapter mAdapter;

    public ItemTouchHelperCallback(ItemTouchHelperAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        /**
         * ItemTouchHelper支持事件方向判断，但是必须重写当前getMovementFlags来指定支持的方向
         * 这里我同时设置了dragFlag为上下左右四个方向，swipeFlag的左右方向
         * 最后通过makeMovementFlags（dragFlag，swipe）创建方向的Flag，
         * 因为我们目前只需要实现拖拽，所以我并未创建swipe的flag
         */
        int dragFlag=ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;//拖拽
        int swipt=ItemTouchHelper.START|ItemTouchHelper.END;//拖拽删除的方向
        return makeMovementFlags(dragFlag,swipt);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;//开启拖动
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;//开启滑动
    }
}
