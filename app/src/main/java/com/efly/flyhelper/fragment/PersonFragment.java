package com.efly.flyhelper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.efly.flyhelper.R;
import com.efly.flyhelper.base.BaseFragment;

/**
 * Created by Administrator on 2016/7/7.
 */

public class PersonFragment extends BaseFragment {

    private View view;
    private static final String TAG = PersonFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_person, null);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    @Override
    public void initView() {

    }
}
