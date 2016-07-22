package com.efly.flyhelper.ui.hotfix;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dodola.rocoofix.RocooFix;
import com.efly.flyhelper.R;
import com.efly.flyhelper.base.BaseActivity;

/**
 * Created by Administrator on 2016/7/21.
 */
public class HotFixActivity extends BaseActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotfix);
        this.findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HelloHack hacks = new HelloHack();
                RocooFix.initPathFromAssetsRuntime(HotFixActivity.this, "patch.jar");
                HelloHack hack1 = new HelloHack();
                Toast.makeText(HotFixActivity.this, hack1.showHello(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
