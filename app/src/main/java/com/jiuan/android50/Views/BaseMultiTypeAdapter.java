package com.jiuan.android50.Views;

import java.util.List;

/**
 * 描述:多条目adapter
 * <p/>作者：wjx
 * <p/>创建时间: 2017/2/23 17:30
 */
public abstract class BaseMultiTypeAdapter<K, T extends BaseViewHolder> extends BaseRecyclerViewAdapter<K, T> {
    public BaseMultiTypeAdapter(List<K> mDatas) {
        super(mDatas);
    }
}
