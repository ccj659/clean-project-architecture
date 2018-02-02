package com.ccj.meizi.debug;

import android.os.Bundle;

import com.ccj.base.base.BaseActivity;
import com.ccj.meizi.R;
import com.ccj.meizi.ui.main.MeiZhiFragment;

/**
 * Created by chenchangjun on 18/1/31.
 */

public class ContainActivity  extends BaseActivity{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contain);
        initFragment();
    }



    private void initFragment() {
        MeiZhiFragment fragment = new MeiZhiFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detail_contain, fragment).commitAllowingStateLoss();

    }
}
