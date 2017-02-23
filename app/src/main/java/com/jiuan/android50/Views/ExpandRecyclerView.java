package com.jiuan.android50.Views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 描述:扩展recyclerview，支持addHead，addFoot，setEmptyView
 * <p/>作者：wjx
 * <p/>创建时间: 2017/2/22 16:44
 */
public class ExpandRecyclerView extends RecyclerView {
    private View headView;
    private View footView;
    private View emptyView;
    public ComposeAdapter mComposeAdapter;

    public ExpandRecyclerView(Context context) {
        super(context);
    }

    public ExpandRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addHeadView(View headView) {
        this.headView = headView;
        mComposeAdapter.notifyItemInserted(0);
    }

    public void addFootView(View footView) {
        this.footView = footView;
        mComposeAdapter.notifyItemInserted(mComposeAdapter.getItemCount() - 1);
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        mComposeAdapter.notifyDataSetChanged();
    }

    @Override
    public void setAdapter(Adapter adapter) {

        if (adapter != null) {
            mComposeAdapter = new ComposeAdapter(adapter);
        }
        super.setAdapter(mComposeAdapter);
    }

    private class ComposeAdapter extends Adapter {
        private int ITEM_TYPE_HEADER = 1;
        private int ITEM_TYPE_FOOTER = 2;
        private int ITEM_TYPE_EMPTY = 3;
        private Adapter mOriginadapter;

        public ComposeAdapter(Adapter adapter) {
            this.mOriginadapter = adapter;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == ITEM_TYPE_HEADER) {
                return new CouposeViewHolder(headView);
            } else if (viewType == ITEM_TYPE_EMPTY) {
                return new CouposeViewHolder(emptyView);
            } else if (viewType == ITEM_TYPE_FOOTER) {
                return new CouposeViewHolder(footView);
            } else {
                return mOriginadapter.onCreateViewHolder(parent, viewType);
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int type = getItemViewType(position);
            if (type == ITEM_TYPE_HEADER || type == ITEM_TYPE_EMPTY || type == ITEM_TYPE_FOOTER) {
                return;
            }
            mOriginadapter.onBindViewHolder(holder, getRealPosition(position));
        }

        @Override
        public int getItemCount() {
            int itemCount = mOriginadapter.getItemCount();
            if (null != emptyView && itemCount == 0) {
                itemCount++;
            }
            if (null != headView) {
                itemCount++;
            }
            if (null != footView) {
                itemCount++;
            }
            return itemCount;
        }

        @Override
        public int getItemViewType(int position) {
            if (null != headView && position == 0) {
                return ITEM_TYPE_HEADER;
            }
            if (null != footView && position == getItemCount() - 1) {
                return ITEM_TYPE_FOOTER;
            }
            if (emptyView != null && mOriginadapter.getItemCount() == 0) {
                return ITEM_TYPE_EMPTY;
            }
            //不能用normal的原因，是因为adapter里面可能就是多条目，所以应该返回子adapter的具体类型
            return mOriginadapter.getItemViewType(getRealPosition(position));
        }

        public int getRealPosition(int position) {
            if (null != headView) {
                return position - 1;
            }
            return position;
        }
    }

    private class CouposeViewHolder extends ViewHolder {
        public CouposeViewHolder(View itemView) {
            super(itemView);
        }
    }
}
