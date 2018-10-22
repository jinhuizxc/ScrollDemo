package com.example.jinhui.scrolldemo;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private MainActivity mActivity;
    private ViewPager viewPager;
    private HorizontalScrollView scrollView;
    private LinearLayout titleLayout;
    private ArrayList<Integer> moveToList;
    private int mTitleMargin;

    private ArrayList<Fragment> fragmentsList;
    private ArrayList<TextView> textViewList;
    private ArrayList<String> titleList;
    private TestFragment fragment;
    private MyViewPagerAdapter mAdapter;

    private int currentPos;
    private String[] strList = new String[]{"title1", "title2", "title3", "title4", "title5", "title6", "title7"};
    private int[] idList = new int[]{0, 1, 2, 3, 4, 5, 6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivity = this;
        mTitleMargin = dip2px(this, 10);
        initView();
        initData();

    }

    private void initData() {
        fragmentsList = new ArrayList<>();
        titleList = new ArrayList<>();
        textViewList = new ArrayList<>();
        moveToList = new ArrayList<>();
        mAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < strList.length; i++) {
            titleList.add(strList[i]);
            fragment = new TestFragment(mActivity, idList[i]);
            fragmentsList.add(fragment);
            // 添加title到layout
            addTitleLayout(titleList.get(i), idList[i]);
        }
        mAdapter.setData(fragmentsList);
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(9);
        textViewList.get(0).setTextColor(Color.rgb(255, 0, 0));
        currentPos = 0;

    }

    private void addTitleLayout(String title, int position) {
        final TextView textView = (TextView) getLayoutInflater().inflate(R.layout.title, null);
        textView.setText(title);
        textView.setTag(position);
        textView.setOnClickListener(new posOnClickListener());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = dip2px(mActivity, mTitleMargin);
        params.rightMargin = dip2px(mActivity, mTitleMargin);
        titleLayout.addView(textView, params);
        textViewList.add(textView);
        int width;
        if (position == 0) {
            width = 0;
            moveToList.add(width);
        } else {
            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            textViewList.get(position - 1).measure(w, h);
            width = textViewList.get(position - 1).getMeasuredWidth() + mTitleMargin * 4;
            moveToList.add(width + moveToList.get(moveToList.size() - 1));
        }
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(mActivity);
        scrollView = (HorizontalScrollView) findViewById(R.id.scrollView);
        titleLayout = (LinearLayout) findViewById(R.id.titleLayout);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        textViewList.get(currentPos).setTextColor(Color.rgb(0, 0, 0));
        textViewList.get(position).setTextColor(Color.rgb(255, 0, 0));
        currentPos = position;
        // scrollTo 到某个位置
        scrollView.scrollTo((int) moveToList.get(position), 0);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    private class posOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if ((int) view.getTag() == currentPos) {
                return;
            }
            textViewList.get(currentPos).setTextColor(Color.rgb(0, 0, 0));
            currentPos = (int) view.getTag();
            textViewList.get(currentPos).setTextColor(Color.rgb(255, 0, 0));
            viewPager.setCurrentItem(currentPos);
        }
    }
}
