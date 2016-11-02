package com.efly.flyhelper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.efly.flyhelper.R;
import com.efly.flyhelper.adapter.ConstantAdapter;
import com.efly.flyhelper.adapter.base.ItemClickListener;
import com.efly.flyhelper.base.BaseFragment;
import com.efly.flyhelper.bean.UserDetail;
import com.efly.flyhelper.view.CustomItemAnimator;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/7.
 */
public class ContactsFragment extends BaseFragment {

    @Bind(R.id.recycleview)
    RecyclerView recycleview;
    private View view;
    private static final String TAG = ContactsFragment.class.getSimpleName();
    private ArrayList<UserDetail> users;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_contacts, null);
        }
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();

    }

    private void initData() {
        users=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            UserDetail resultBean =new UserDetail();
            resultBean.Users_PersonName="小明"+i;
            resultBean.Users_CellPhoneNum="1234567891";
            users.add(resultBean);
        }
        ConstantAdapter constantAdapter=new ConstantAdapter(users,getActivity());
        recycleview.setLayoutManager(new LinearLayoutManager(getActivity()));//这里用线性显示 类似于listview
        recycleview.setItemAnimator(new CustomItemAnimator());
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));//这里用线性宫格显示 类似于grid view
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//这
        recycleview.setAdapter(constantAdapter);
        constantAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(int position) {
                Log.e(TAG,"onClick-->"+position);
                Toast.makeText(getActivity(),"onClick-->"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void initView() {

    }
}
