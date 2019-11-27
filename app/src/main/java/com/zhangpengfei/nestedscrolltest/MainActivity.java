package com.zhangpengfei.nestedscrolltest;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewTreeObserver;


import com.zhangpengfei.nestedscrolltest.common.AdapterItem;
import com.zhangpengfei.nestedscrolltest.common.BaseAdapter;
import com.zhangpengfei.nestedscrolltest.common.BaseViewHolder;
import com.zhangpengfei.nestedscrolltest.items.PageItem;
import com.zhangpengfei.nestedscrolltest.items.ParentItem;
import com.zhangpengfei.nestedscrolltest.model.NestedViewModel;
import com.zhangpengfei.nestedscrolltest.model.PageVO;
import com.zhangpengfei.nestedscrolltest.viewholder.ImageViewHolder;
import com.zhangpengfei.nestedscrolltest.viewholder.PagerViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.zhangpengfei.nestedscrolltest.ViewType.TYPE_PAGER;
import static com.zhangpengfei.nestedscrolltest.ViewType.TYPE_PARENT;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;

    private NestedViewModel viewModel;

    private NestedScrollLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_generation);
        container = findViewById(R.id.rootview);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        container.setRootList(recyclerView);
        container.setTarget(this);
        initAdapter();
        viewModel = ViewModelProviders.of(this).get(NestedViewModel.class);
        container.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int height = container.getMeasuredHeight();
                viewModel.getPagerHeight().setValue(height);
            }
        });
    }

    private void initAdapter() {
        SparseArray<Class<? extends BaseViewHolder>> viewHolders = new SparseArray<>();
        viewHolders.put(TYPE_PARENT, ImageViewHolder.class);
        viewHolders.put(TYPE_PAGER, PagerViewHolder.class);
        int[] ids = new int[]{ R.mipmap.pic5};
        List<AdapterItem> itemList = new ArrayList<>();
        for (int id : ids) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
            itemList.add(new ParentItem(bitmap));
        }
        List<PageVO> pageList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pageList.add(new PageVO(Color.WHITE, "tab" + i));
        }
        itemList.add(new PageItem(pageList));
        adapter = new BaseAdapter(itemList, this, viewHolders);
        recyclerView.setAdapter(adapter);
    }
}
