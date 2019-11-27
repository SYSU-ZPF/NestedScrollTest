package com.zhangpengfei.nestedscrolltest.viewholder;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;


import com.zhangpengfei.nestedscrolltest.R;
import com.zhangpengfei.nestedscrolltest.common.BaseViewHolder;
import com.zhangpengfei.nestedscrolltest.common.HolderAnnotation;


@HolderAnnotation(layoutId = R.layout.item_parent_holder)
public class ImageViewHolder extends BaseViewHolder<Bitmap> {

    private ImageView imageView;

    private Bitmap bitmap;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void initViews() {
        imageView = itemView.findViewById(R.id.imageview);
    }

    @Override
    public void bindView(Bitmap data) {
        if (bitmap == data) {
            return;
        }
        bitmap = data;
        imageView.setImageBitmap(bitmap);
    }
}
