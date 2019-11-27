package com.zhangpengfei.nestedscrolltest.items;

import com.zhangpengfei.nestedscrolltest.ViewType;
import com.zhangpengfei.nestedscrolltest.common.AdapterItem;


public class TextItem implements AdapterItem<String> {

    private String text;

    public TextItem(String text) {
        this.text = text;
    }

    @Override
    public String getDataModel() {
        return text;
    }

    @Override
    public int getViewType() {
        return ViewType.TYPE_TEXT;
    }
}
