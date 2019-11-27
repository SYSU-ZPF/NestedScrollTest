package com.zhangpengfei.nestedscrolltest.common;


public interface AdapterItem<T> {

    T getDataModel();

    int getViewType();
}
