package com.example.jinhui.scrolldemo;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

/**
 * Email: 1004260403@qq.com
 * Created by jinhui on 2018/10/22.
 */


public class TestFragment extends Fragment {

    private Context mContext;
    private View mView;
    private int type;

    public TestFragment(Context context, int type) {
        this.mContext = context;
        this.type = type;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Toast.makeText(mContext, type + "", Toast.LENGTH_SHORT).show();
        }
    }
}
