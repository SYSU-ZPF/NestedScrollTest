package com.zhangpengfei.nestedscrolltest;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangpengfei.nestedscrolltest.common.AdapterItem;
import com.zhangpengfei.nestedscrolltest.common.BaseAdapter;
import com.zhangpengfei.nestedscrolltest.common.BaseViewHolder;
import com.zhangpengfei.nestedscrolltest.items.TextItem;
import com.zhangpengfei.nestedscrolltest.model.NestedViewModel;
import com.zhangpengfei.nestedscrolltest.viewholder.TextViewHolder;

import java.util.ArrayList;
import java.util.List;


public class SubFragment extends Fragment {

    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;

    private NestedViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goods_list, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        int color = getArguments().getInt("color");
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setBackgroundColor(color);

        SparseArray<Class<? extends BaseViewHolder>> viewHolders = new SparseArray<>();
        viewHolders.put(ViewType.TYPE_TEXT, TextViewHolder.class);
        List<AdapterItem> itemList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            itemList.add(new TextItem("text" + i));
        }
        adapter = new BaseAdapter(itemList, view.getContext(), viewHolders);
        recyclerView.setAdapter(adapter);


    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        initViewModel();
        if (isVisibleToUser && trackFragment() && viewModel != null) {
            viewModel.getChildList().setValue(recyclerView);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initViewModel();
        if (trackFragment() && viewModel != null) {
            viewModel.getChildList().setValue(recyclerView);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        initViewModel();
        if (!hidden && trackFragment() && viewModel != null) {
            viewModel.getChildList().setValue(recyclerView);
        }
    }

    private void initViewModel() {
        if (viewModel == null && getActivity() != null) {
            viewModel = ViewModelProviders.of(getActivity()).get(NestedViewModel.class);
        }
    }

    private boolean trackFragment() {
        if (getView() == null || !(getView().getParent() instanceof View)) {
            return false;
        }
        View parent = (View) getView().getParent();
        if (parent instanceof ViewPager) {
            ViewPager viewPager = (ViewPager) parent;
            int currentItem = viewPager.getCurrentItem();
            //这里需要注意，SubPagerAdapter中，需要把每个Fragment的position传入Arguments
            int position = getArguments() != null ? getArguments().getInt("position", -1) : -1;
            return currentItem == position;
        }
        return false;
    }
}
