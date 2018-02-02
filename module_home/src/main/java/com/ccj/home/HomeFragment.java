package com.ccj.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ccj.base.Constants;
import com.ccj.base.RouterConstants;
import com.ccj.base.base.BaseApplication;

/**
 * Created by chenchangjun on 18/1/25.
 */
@Route(path = RouterConstants.HOME_MUDULE_FRAGMENT_HOME_HOME)
public class HomeFragment extends Fragment implements View.OnClickListener {


    TextView button;
    Button button2;
    Button button3;
    Button button4;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.home_fragment_haojia_home, null);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button2= (Button) view.findViewById(R.id.button2);
        button3= (Button) view.findViewById(R.id.button3);
        button4= (Button) view.findViewById(R.id.button4);

        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

    }



    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button2) {
            navigateToLogin();
        } else if (i == R.id.button3) {
            navigateMeiziDetail();
        } else if (i == R.id.button4) {
            navigateTakePhoto();

        }
    }




    private void navigateMeiziDetail() {
        ARouter.getInstance().build(RouterConstants.MEIZI_MUDULE_ACTIVITY_MEIZI_DETAIL).
                withString(Constants.PARAMS_REQUEST_FOR_DETAIL, "http://7xi8d6.com1.z0.glb.clouddn.com/20180129074038_O3ydq4_Screenshot.jpeg").
                navigation(getActivity());

    }


    private void navigateToLogin() {
        ARouter.getInstance().build(RouterConstants.USER_MOUDLE_ACTIVITY).
                withString(Constants.START_LOGIN_WITH_PARAMS, "I am params from HomeFragment").
                navigation(getActivity(), Constants.REQUEST_START_LOGIN);
    }

    private void navigateTakePhoto() {
        ARouter.getInstance().
                build(RouterConstants.VIDEO_MUDULE_ACTIVITY).
                withString(Constants.START_LOGIN_WITH_PARAMS, "I am params from HomeFragment").
                navigation();

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_START_LOGIN) {

            if (data == null) {
                return;
            }
            String str = data.getStringExtra(Constants.PARAMS_RESULT_FROM_LOGIN);

            if (str == null) {
                return;
            }

            button.setText(str);
            BaseApplication.showToast(str);
        }

    }

}