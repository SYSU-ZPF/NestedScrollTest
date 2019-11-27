package com.zhangpengfei.nestedscrolltest.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.zhangpengfei.nestedscrolltest.R;
import com.zhangpengfei.nestedscrolltest.common.BaseViewHolder;
import com.zhangpengfei.nestedscrolltest.common.HolderAnnotation;


@HolderAnnotation(layoutId = R.layout.item_text)
public class TextViewHolder extends BaseViewHolder<String> {

    private TextView textView;

    public TextViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void initViews() {
        textView = itemView.findViewById(R.id.textview);
    }

    @Override
    public void bindView(String data) {
        textView.setText(data);
    }
}
