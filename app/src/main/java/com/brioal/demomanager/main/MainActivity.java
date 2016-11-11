package com.brioal.demomanager.main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.brioal.demomanager.R;
import com.brioal.demomanager.base.BaseActivity;
import com.brioal.demomanager.list.ui.DemoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.main_container)
    RelativeLayout mMainContainer;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private DemoFragment mCurrentFragment = null;
    private FragmentManager mFragmentManager;

    private long mLastClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.DemoTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        ButterKnife.bind(this);
        initView();
        init();
    }

    private void init() {
        mFragmentManager = getSupportFragmentManager();
        mToolbar.setTitle("本地Demo列表");
        changeFragment(DemoFragment.getInstance(DemoFragment.TYPE_LOCAL));
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (System.currentTimeMillis() - mLastClick < 2000) {
            super.onBackPressed();
        } else {
            Snackbar.make(mMainContainer, "再按一次返回键退出", Snackbar.LENGTH_SHORT).show();
            mLastClick = System.currentTimeMillis();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_local) {
            changeFragment(DemoFragment.getInstance(DemoFragment.TYPE_LOCAL));
            mToolbar.setTitle("本地Demo列表");
        } else if (id == R.id.nav_net) {
            changeFragment(DemoFragment.getInstance(DemoFragment.TYPE_NET));
            mToolbar.setTitle("网络Demo列表");
        } else if (id == R.id.nav_night) {
            changeTheme();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    //改变Fragment
    private void changeFragment(DemoFragment demoFragment) {
        if (demoFragment == mCurrentFragment) {
            return;
        }
        if (demoFragment.isAdded()) {
            if (mCurrentFragment != null) {
                mFragmentManager.beginTransaction().hide(mCurrentFragment).commit();
            }
            mFragmentManager.beginTransaction().show(demoFragment).commit();
        } else {
            mFragmentManager.beginTransaction().add(mMainContainer.getId(), demoFragment).commit();
        }
        mCurrentFragment = demoFragment;
    }

    //改变模式
    private void changeTheme() {
        int currentMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        getDelegate().setLocalNightMode(currentMode == Configuration.UI_MODE_NIGHT_NO
                ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        recreate();
    }


}
