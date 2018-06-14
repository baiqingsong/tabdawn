package com.yipai.tabdawn.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.yipai.tabdawn.R;
import com.yipai.tabdawn.ViewPagerSlide;
import com.yipai.tabdawn.adapter.BottomTabAdapter;
import com.yipai.tabdawn.fragment.TabFragment1;
import com.yipai.tabdawn.fragment.TabFragment2;
import com.yipai.tabdawn.fragment.TabFragment3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 90449 on 2018/6/14.
 */

public class BottomTabActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPagerSlide viewPager;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab);
        initView();
        addListener();
    }
    private void initView(){
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
    }
    private void addListener(){
        viewPager.setSlide(false);
        fragments = new ArrayList<>();
        fragments.add(new TabFragment1());
        fragments.add(new TabFragment2());
        fragments.add(new TabFragment3());
        BottomTabAdapter adapter = new BottomTabAdapter(getSupportFragmentManager(), fragments, new String[]{"首页", "礼物卡片", "我的"});
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        for(int i = 0; i < tabLayout.getTabCount(); i ++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            Drawable drawable = null;
            switch (i){
                case 0:
                    drawable = getResources().getDrawable(R.drawable.bg_home);
                    break;
                case 1:
                    drawable = getResources().getDrawable(R.drawable.bg_gift_card);
                    break;
                case 2:
                    drawable = getResources().getDrawable(R.drawable.bg_mine);
                    break;
            }
            tab.setIcon(drawable);
        }
    }
}
