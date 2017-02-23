package com.jiuan.android50.Views;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static android.support.v7.widget.RecyclerView.NO_POSITION;

/**
 * 描述:基类适配器，可以设置头，尾和空布局
 * <p/>作者：wjx
 * <p/>创建时间: 2017/2/23 13:18
 */
public abstract class BaseRecyclerViewAdapter<T, K extends BaseViewHolder> extends RecyclerView.Adapter<K> {
    private int ITEM_TYPE_HEADER = 0x00000111;
    private int ITEM_TYPE_FOOTER = 0x00000333;
    private int ITEM_TYPE_EMPTY = 0x00000555;
    private View headView;
    private View footView;
    private View emptyView;
    /**
     * 数据
     */
    protected List<T> mDatas;
    /**
     * 是否允许加载更多
     */
    private boolean mOpenLoadMore = false;


    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    OnItemClickListener mListener;

    public BaseRecyclerViewAdapter(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public K onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = null;
        if (viewType == ITEM_TYPE_HEADER) {
            holder = new BaseViewHolder(headView);
        } else if (viewType == ITEM_TYPE_EMPTY) {
            holder = new BaseViewHolder(emptyView);
        } else if (viewType == ITEM_TYPE_FOOTER) {
            holder = new BaseViewHolder(footView);
        } else {
            Context mContext = parent.getContext();
            holder = BaseViewHolder.creat(mContext, getItemLayout(), parent);
            final BaseViewHolder finalHolder = holder;
            holder.getmConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = finalHolder.getAdapterPosition();
                    if (position == NO_POSITION) {
                        return;
                    }
                    int realPosition = getRealPosition(position);
                    if (mListener != null) {
                        mListener.onItemClick(finalHolder.getmConvertView(), realPosition);
                    }
                }
            });
        }
        return (K) holder;
    }

    /**
     * 获取实际位置，去除掉head
     *
     * @param position
     * @return
     */
    public int getRealPosition(int position) {
        if (null != headView) {
            return position - 1;
        }
        return position;
    }

    /**
     * 获取布局
     *
     * @return
     */
    public abstract int getItemLayout();

    @Override
    public void onBindViewHolder(K holder, int position) {
        K viewHolder = holder;
        int type = getItemViewType(position);
        if (type == ITEM_TYPE_HEADER || type == ITEM_TYPE_EMPTY || type == ITEM_TYPE_FOOTER) {
            return;
        }
        convert(viewHolder, mDatas.get(getRealPosition(position)));
    }

    protected abstract void convert(K viewHolder, T t);

    @Override
    public int getItemCount() {
        int itemCount = mDatas == null ? 0 : mDatas.size();
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
        if (emptyView != null && getItemCount() == 0) {
            return ITEM_TYPE_EMPTY;
        }
        return getDefaultItemViewType(getRealPosition(position));
    }

    /**
     * 根据位置获取viewType，如果是多条目，子类可以覆写该类
     *
     * @param position
     * @return
     */
    protected int getDefaultItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void addHeadView(View headView) {
        this.headView = headView;
        notifyItemInserted(0);
    }

    public void addFootView(View footView) {
        this.footView = footView;
        notifyItemInserted(getItemCount() - 1);
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        notifyDataSetChanged();
    }

    public void setLoadMoreEnabled(boolean mOpenLoadMore) {
        this.mOpenLoadMore = mOpenLoadMore;
    }

    @Override
    public void onViewAttachedToWindow(K holder) {
        //http://blog.csdn.net/qibin0506/article/details/49716795
        super.onViewAttachedToWindow(holder);
        int viewType = holder.getItemViewType();
        if (viewType == ITEM_TYPE_FOOTER || viewType == ITEM_TYPE_HEADER) {
            setFullPan(holder);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager){
            GridLayoutManager gridLayoutManager= (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return 0;
                }
            });
        }
    }

    /**
     * 如果是表头或者底部栏，并且是纵向显示，则占据一行
     *
     * @param holder
     */
    private void setFullPan(K holder) {
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        if(params!=null && params instanceof StaggeredGridLayoutManager.LayoutParams){
            ((StaggeredGridLayoutManager.LayoutParams) params).setFullSpan(true);
        }
    }

    /**
     * 描述:点击回调
     * <p/>作者：wjx
     * <p/>创建时间: 2017/2/5 11:28
     */
    public interface OnItemClickListener {
        /**
         * @param root     点击的view
         * @param position 点击的位置
         */
        void onItemClick(View root, int position);
    }
}
