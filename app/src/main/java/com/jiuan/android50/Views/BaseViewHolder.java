package com.jiuan.android50.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/2/23 13:25
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;

    public static BaseViewHolder creat(Context context, int layoutId, ViewGroup parent) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new BaseViewHolder(itemView);
    }

    public static BaseViewHolder creat(View itemView) {
        return new BaseViewHolder(itemView);
    }

    public BaseViewHolder(View itemView) {
        super(itemView);
        mConvertView = itemView;
        mViews=new SparseArray<>();
    }

    public View getmConvertView() {
        return mConvertView;
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (null == view) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public void setText(int viewId, String str) {
        TextView view = getView(viewId);
        view.setText(str);
    }
}
