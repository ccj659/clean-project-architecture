package com.ccj.video;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.base.RouterConstants;

/**
 * Created by chenchangjun on 18/1/25.
 */
@Route(path= RouterConstants.VIDEO_MUDULE_FRAGMENT_HOME_VIDEO)
public class VideoFragment extends Fragment {


    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.video_fragment_video_home, null);
        }
        return view;
    }


}
