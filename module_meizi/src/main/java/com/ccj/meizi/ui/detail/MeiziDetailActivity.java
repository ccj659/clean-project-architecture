package com.ccj.meizi.ui.detail;

import android.os.Bundle;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.base.Constants;
import com.ccj.base.RouterConstants;
import com.ccj.base.base.BaseActivity;
import com.ccj.meizi.R;
import com.squareup.picasso.Picasso;

/**
 * Created by chenchangjun on 18/2/1.
 */

@Route(path = RouterConstants.MEIZI_MUDULE_ACTIVITY_MEIZI_DETAIL)
public class MeiziDetailActivity extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizi_detail);
        ImageView imageView= (ImageView) findViewById(R.id.image);


      String url=  getIntent().getStringExtra(Constants.PARAMS_REQUEST_FOR_DETAIL);

        Picasso.with(this)
                .load(url+"")
                .into(imageView);
    }


}
