package com.zhangpengfei.nestedscrolltest.common;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;



public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void initViews();

    public abstract void bindView(T data);


}
