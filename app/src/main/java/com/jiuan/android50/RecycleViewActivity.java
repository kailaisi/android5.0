package com.jiuan.android50;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiuan.android50.Views.BaseRecyclerViewAdapter;
import com.jiuan.android50.Views.BaseViewHolder;
import com.jiuan.android50.domain.Cheeses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wu on 2015/12/1.
 */
public class RecycleViewActivity extends AppCompatActivity {
    private List<String> mData;
    private RecyclerAdapter mAdapter;
    private RecyclerView idRecyclerview;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyle);
        this.idRecyclerview = (RecyclerView) this.findViewById(R.id.id_recyclerview);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        idRecyclerview.setLayoutManager(new LinearLayoutManager(this));//listview样式
//        idRecyclerview.setLayoutManager(new GridLayoutManager(this,3));//gridview样式
        idRecyclerview.setLayoutManager(new LinearLayoutManager(this));//固定行或者固定列
        idRecyclerview.setItemAnimator(new DefaultItemAnimator());
        initData();
        idRecyclerview.setHasFixedSize(true);
        mAdapter = new RecyclerAdapter(RecycleViewActivity.this, mData);
        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Snackbar.make(view, position + "", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Snackbar.make(view, position + "", Snackbar.LENGTH_LONG).show();
            }
        });
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300, 400);
        textView.setLayoutParams(params);
        textView.setText("空");
        BaseRecyclerViewAdapter adapter=new BaseRecyclerViewAdapter<String,BaseViewHolder>(mData,this, mOpenLoadMore) {


            @Override
            public int getItemLayout() {
                return R.layout.item_recyle;
            }

            @Override
            protected void convert(BaseViewHolder viewHolder, String s) {
                viewHolder.setText(R.id.tv_name,s);
            }
        };
        adapter.setmListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View root, int position) {
                Snackbar.make(root, position + "", Snackbar.LENGTH_LONG).show();
            }
        });
        adapter.addFootView(textView);
        adapter.addHeadView(textView);
        idRecyclerview.setAdapter(adapter);
        //将拖拽功能和recycleview进行绑定
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
        ItemTouchHelper help = new ItemTouchHelper(callback);
        help.attachToRecyclerView(idRecyclerview);

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
