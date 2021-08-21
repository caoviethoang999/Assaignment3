package com.example.hoangcv2_gym;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private int numPage=3;
    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new ArmWorkOutFragment();
            case 1:return new AbsWorkOutFragment();
            case 2:return new ChestWorkOutFragment();
        }
        return new ArmWorkOutFragment();
    }
    @Override
    public int getCount() {
        return numPage;
    }
}
