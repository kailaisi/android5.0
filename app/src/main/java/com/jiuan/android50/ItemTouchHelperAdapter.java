package com.jiuan.android50;

/**
 * 拖拽的接口，进行数据处理
 * Created by wjx on 2016/12/22.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int from, int to);

    void onItemDismiss(int position);
}
