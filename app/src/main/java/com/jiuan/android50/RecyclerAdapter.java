package com.jiuan.android50;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nineoldandroids.animation.ObjectAnimator;

import java.util.Collections;
import java.util.List;

/**
 * Created by wu on 2015/12/7.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.myViewHolder> implements ItemTouchHelperAdapter {
    private OnItemClickListener mListener;
    private int lastPosition = -1;
    private Context mContext;
    private List<String> mData;

    public RecyclerAdapter(Context context, List<String> mData) {
        mContext = context;
        this.mData = mData;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    public void addData(int position) {
        mData.add(position, "Insert One");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public String getData(int position) {
        return mData.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 3 == 0) {
            return R.layout.item_recyle;
        } else {
            return R.layout.item_recyle_left;
        }
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(viewType, parent, false);

        final myViewHolder holder = new myViewHolder(view);
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, holder.getAdapterPosition() + "", Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, final int position) {
        holder.bind(mData.get(position), position);
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

    @Override
    public boolean onItemMove(int from, int to) {
        Collections.swap(mData, from, to);
        notifyItemMoved(from, to);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public CardView container;

        public myViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_name);
            container = (CardView) itemView.findViewById(R.id.item_layout_container);
        }

        public void bind(String s, int position) {
            tv.setText(s);
            setAnimation(container, position);
        }

        public void setAnimation(View viewToAnimate, int position) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(viewToAnimate, "translationX", -viewToAnimate.getMeasuredWidth(), 0);
            animator.setDuration(300);
            animator.start();
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
}
