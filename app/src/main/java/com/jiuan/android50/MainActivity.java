
package com.jiuan.android50;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jiuan.android50.design.MaterialActivity;
import com.jiuan.android50.utils.StatusBarCompat;


public class MainActivity extends Activity {
    private android.widget.Button btnmaterial;
    private android.widget.Button btnrecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnrecycleView = (Button) findViewById(R.id.btn_recycleView);
        this.btnmaterial = (Button) findViewById(R.id.btn_material);
        initData();
        StatusBarCompat.compat(this);
    }

    private void initData() {
        btnmaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MaterialActivity.class));
            }
        });
        btnrecycleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RecycleViewActivity.class));
            }
        });
    }
}
