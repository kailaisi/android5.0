package com.jiuan.android50;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jiuan.android50.domain.Cheeses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.wasabeef.recyclerview.animators.ScaleInAnimator;

/**
 * Created by wu on 2015/12/1.
 */
public class RecycleViewActivity extends AppCompatActivity {
    private List<String> mData;
    private RecyleAdapter mAdapter;
    private RecyclerView idRecyclerview;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyle);
        this.idRecyclerview = (RecyclerView) this.findViewById(R.id.id_recyclerview);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        idRecyclerview.setLayoutManager(new LinearLayoutManager(this));//listview样式
        // idRecyclerview.setLayoutManager(new GridLayoutManager(this,3));//gridview样式
        // idRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));//固定行或者固定列
        idRecyclerview.setItemAnimator(new ScaleInAnimator());
        initData();
        idRecyclerview.setHasFixedSize(true);
        mAdapter = new RecyleAdapter(RecycleViewActivity.this,mData);
        mAdapter.setOnItemClickListener(new RecyleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Snackbar.make(view,position+"",Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Snackbar.make(view,position+"",Snackbar.LENGTH_LONG).show();
            }
        });
        idRecyclerview.setAdapter(mAdapter);

    }


    private void initData() {
        mData = new ArrayList<>();
        mData.addAll(Arrays.asList(Cheeses.NAMES));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_action_add:
                mAdapter.addData(3);
                break;
            case R.id.id_action_delete:
                mAdapter.removeData(3);
                break;
        }
        return true;
    }

}
