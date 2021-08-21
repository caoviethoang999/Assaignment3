package com.example.hoangcv2_gym;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }
    public void initView(){
        tabLayout=findViewById(R.id.tablayoutHome);
        viewPager=findViewById(R.id.viewpagerHome);
        adapter=new FragmentAdapter(getSupportFragmentManager(), FragmentAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Arms").setIcon(R.drawable.arm_workout);
        tabLayout.getTabAt(1).setText("Abs").setIcon(R.drawable.abs_workout);
        tabLayout.getTabAt(2).setText("Chest").setIcon(R.drawable.chest_workout);
    }
}