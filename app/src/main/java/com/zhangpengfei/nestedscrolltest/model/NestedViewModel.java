package com.zhangpengfei.nestedscrolltest.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v7.widget.RecyclerView;
import android.view.View;



public class NestedViewModel extends ViewModel {

    private MutableLiveData<Integer> pagerHeight;

    private MutableLiveData<View> childView;

    private MutableLiveData<RecyclerView> childList;

    public MutableLiveData<Integer> getPagerHeight() {
        if (pagerHeight == null) {
            pagerHeight = new MutableLiveData<>();
        }
        return pagerHeight;
    }

    public MutableLiveData<View> getChildView() {
        if (childView == null) {
            childView = new MutableLiveData<>();
        }
        return childView;
    }

    public MutableLiveData<RecyclerView> getChildList() {
        if (childList == null) {
            childList = new MutableLiveData<>();
        }
        return childList;
    }
}
