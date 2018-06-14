package com.yipai.tabdawn.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.yipai.tabdawn.R;
import com.yipai.tabdawn.ViewPagerSlide;
import com.yipai.tabdawn.adapter.TopTabAdapter;
import com.yipai.tabdawn.fragment.TabFragment1;
import com.yipai.tabdawn.fragment.TabFragment2;
import com.yipai.tabdawn.fragment.TabFragment3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 90449 on 2018/6/14.
 */

public class TopTabActivity extends AppCompatActivity{
    private TabLayout tabLayout;
    private ViewPagerSlide viewPager;
    private TopTabAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_tab);
        initView();
        addListener();
    }
    private void initView(){
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
    }
    private void addListener(){
        viewPager.setSlide(true);
        List<Fragment> fragments = new ArrayList<>();
        Fragment fragment1 = new TabFragment1();
        Fragment fragment2 = new TabFragment2();
        Fragment fragment3 = new TabFragment3();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        mAdapter = new TopTabAdapter(getSupportFragmentManager(), fragments, new String[]{"tab1", "tab2", "tab3"});
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
