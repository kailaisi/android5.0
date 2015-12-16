package com.jiuan.android50.design;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiuan.android50.R;
import com.jiuan.android50.RecyleAdapter;
import com.jiuan.android50.domain.Cheeses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * material的vp中使用的fragment
 * Created by wu on 2015/12/7.
 */
public class ListFragment extends Fragment {
    private List<String> mData;
    private RecyclerView recyclerView;
    private RecyleAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyle, container, false);
        if(Build.VERSION.SDK_INT>=14) {
            view.setFitsSystemWindows(true);
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);
        mData = new ArrayList<>();
        mData.addAll(Arrays.asList(Cheeses.NAMES));
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        mAdapter=new RecyleAdapter(getActivity(), mData);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecyleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(),detailedActivity.class));
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Snackbar.make(view,mAdapter.getData(position),Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
