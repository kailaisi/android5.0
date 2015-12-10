package com.jiuan.android50.design;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.jiuan.android50.R;
import com.jiuan.android50.utils.StatusBarCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * material的主界面
 * Created by wu on 2015/12/1.
 */
public class MaterialActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private android.support.v4.view.ViewPager vp;
    private android.support.design.widget.TabLayout tabs;
    private android.support.design.widget.AppBarLayout appbarlayout;
    private MyFragmentPagerAdapter mAdapter;
    private android.support.design.widget.NavigationView navigation;
    private android.support.v4.widget.DrawerLayout drawerlayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_all);
        intiViews();
        initToolbar();
        initViewPager();
        StatusBarCompat.compat(this);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationIcon(R.mipmap.ic_menu);
        }
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerToggle.syncState();
        drawerlayout.setDrawerListener(mDrawerToggle);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                Snackbar.make(navigation,item.getItemId()+item.getTitle().toString()+"",Snackbar.LENGTH_LONG).show();
                drawerlayout.closeDrawers();
                return true;
            }
        });
    }

    private void intiViews() {
        this.drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        this.navigation = (NavigationView) findViewById(R.id.navigation);
        this.appbarlayout = (AppBarLayout) findViewById(R.id.appbar);
        this.tabs = (TabLayout) findViewById(R.id.tabs);
        this.vp = (ViewPager) findViewById(R.id.vp);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void initViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ListFragment());
        fragments.add(new ListFragment());
        fragments.add(new ListFragment());
        fragments.add(new ListFragment());
        fragments.add(new ListFragment());
        fragments.add(new ListFragment());
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments, this);
        vp.setAdapter(mAdapter);
        tabs.setupWithViewPager(vp);
        tabs.setTabTextColors(Color.GREEN, Color.RED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_overaction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerlayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
