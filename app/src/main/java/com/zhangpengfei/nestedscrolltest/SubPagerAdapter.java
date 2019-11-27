package com.zhangpengfei.nestedscrolltest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.zhangpengfei.nestedscrolltest.model.PageVO;

import java.util.List;


public class SubPagerAdapter extends FragmentStatePagerAdapter {

    private List<PageVO> itemList;

    public SubPagerAdapter(FragmentManager fm, List<PageVO> itemList) {
        super(fm);
        this.itemList = itemList;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new SubFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("color", itemList.get(position).getColor());
        bundle.putInt("position",position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return itemList == null ? 0 : itemList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return itemList.get(position).getTitle();
    }
}
