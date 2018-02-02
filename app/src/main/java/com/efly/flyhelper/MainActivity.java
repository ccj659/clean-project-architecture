package com.efly.flyhelper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.RouterConstants;
import com.efly.flyhelper.adapter.FragmentAdapter;

import java.util.LinkedList;
import java.util.List;


public class MainActivity extends BaseActivity {

    private ViewPager mPager;
    private List<Fragment> mFragments = new LinkedList<>();
    private FragmentAdapter mAdapter;
    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int i = item.getItemId();
            if (i == R.id.navigation_home) {
                mPager.setCurrentItem(0);
                return true;
            } else if (i == R.id.navigation_dashboard) {
                mPager.setCurrentItem(1);
                return true;
            } else if (i == R.id.navigation_notifications) {
                mPager.setCurrentItem(2);
                return true;
            }
            return false;
        }

    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        mPager = (ViewPager) findViewById(R.id.container_pager);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        initViewPager();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initViewPager() {


        Fragment home = (Fragment) ARouter.getInstance().build(RouterConstants.HOME_MUDULE_FRAGMENT_HOME_HOME).navigation();
        Fragment meizi = (Fragment) ARouter.getInstance().build(RouterConstants.MEIZI_MUDULE_FRAGMENT_HOME_MEIZI).navigation();
        Fragment user = (Fragment) ARouter.getInstance().build(RouterConstants.USER_MUDULE_FRAGMENT_HOME_USER).navigation();
        mFragments.add(home);
        mFragments.add(meizi);
        mFragments.add(user);

        mAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments);
        mPager.setAdapter(mAdapter);
    }


}
