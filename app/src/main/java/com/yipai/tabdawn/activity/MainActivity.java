package com.yipai.tabdawn.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yipai.tabdawn.R;

public class MainActivity extends AppCompatActivity {

    private TextView tvTopTab;
    private TextView tvBottomTab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        addListener();
    }
    private void initView(){
        tvTopTab = findViewById(R.id.tv_top_tab);
        tvBottomTab = findViewById(R.id.tv_bottom_tab);
    }
    private void addListener(){
        tvTopTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TopTabActivity.class);
                startActivity(intent);
            }
        });
        tvBottomTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BottomTabActivity.class);
                startActivity(intent);
            }
        });
    }
}
