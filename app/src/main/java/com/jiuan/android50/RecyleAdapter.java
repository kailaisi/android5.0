package com.jiuan.android50;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.ObjectAnimator;

import java.util.List;

/**
 * Created by wu on 2015/12/7.
 */
public class RecyleAdapter extends RecyclerView.Adapter<RecyleAdapter.myViewHolder> {
    private OnItemClickListener mListener;
    private int lastPosition = -1;
    private Context mContext;
    private List<String> mData;

    public RecyleAdapter(Context context, List<String> mData) {
        mContext = context;
        this.mData = mData;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public void clearDatas() {
        int size = mData.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                mData.remove(0);
            }

            notifyItemRangeRemoved(0, size);
        }
    }

    public void addDatas(List<String> datas) {
        mData.addAll(datas);
        notifyItemRangeInserted(0, datas.size() - 1);
    }

    public void addData(int position) {
        mData.add(position, "Insert One");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public String getData(int position){
        return  mData.get(position);
    }
    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recyle, parent, false);
        myViewHolder holder = new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, final int position) {
        holder.tv.setText(mData.get(position));
        setAnimation(holder.container, position);
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, position);
                }
            }
        });
        holder.tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mListener != null) {
                    mListener.onItemLongClick(v, position);
                }
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(viewToAnimate, "translationX", -viewToAnimate.getMeasuredWidth(), 0);
            animator.setDuration(300);
            animator.start();
        }
        lastPosition = position;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onViewDetachedFromWindow(myViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.container.clearAnimation();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public CardView container;

        public myViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_name);
            container = (CardView) itemView.findViewById(R.id.item_layout_container);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
}