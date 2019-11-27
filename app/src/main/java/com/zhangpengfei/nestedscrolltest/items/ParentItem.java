package com.zhangpengfei.nestedscrolltest.items;

import android.graphics.Bitmap;

import com.zhangpengfei.nestedscrolltest.ViewType;
import com.zhangpengfei.nestedscrolltest.common.AdapterItem;


public class ParentItem implements AdapterItem<Bitmap> {
    private Bitmap bitmap;

    public ParentItem(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public Bitmap getDataModel() {
        return bitmap;
    }

    @Override
    public int getViewType() {
        return ViewType.TYPE_PARENT;
    }
}
