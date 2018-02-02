package com.ccj.meizi.holder;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ccj.base.Constants;
import com.ccj.base.RouterConstants;
import com.ccj.base.adapter.item.AdapterItem;
import com.ccj.meizi.R;
import com.ccj.meizi.bean.Meizhi;
import com.squareup.picasso.Picasso;

/**
 * 通用brand  card
 */
public class MeiziItemHolder implements AdapterItem<Meizhi.MeiziItemBean>, View.OnClickListener {

    private static final String TAG = MeiziItemHolder.class.getSimpleName();

    private Activity mActivity;
    private Meizhi.MeiziItemBean model;

    private ImageView imageView;


    public MeiziItemHolder(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_meizi_item;
    }

    @Override
    public void bindViews(View root) {
        imageView = (ImageView) root.findViewById(R.id.imageView);
        root.setOnClickListener(this);
    }

    @Override
    public void setViews() {

    }

    @Override
    public void onClick(View v) {
       ARouter.getInstance().
               build(RouterConstants.MEIZI_MUDULE_ACTIVITY_MEIZI_DETAIL).
               withString(Constants.PARAMS_REQUEST_FOR_DETAIL,model.url).
               navigation();

    }

    @Override
    public void handleData(Meizhi.MeiziItemBean item, int position) {
        this.model = item;

        Picasso.with(mActivity)
                .load(item.url)
                .into(imageView);
    }


}