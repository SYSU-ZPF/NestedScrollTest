package com.zhangpengfei.nestedscrolltest.viewholder;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;




import com.zhangpengfei.nestedscrolltest.R;
import com.zhangpengfei.nestedscrolltest.SubPagerAdapter;
import com.zhangpengfei.nestedscrolltest.common.BaseViewHolder;
import com.zhangpengfei.nestedscrolltest.common.HolderAnnotation;
import com.zhangpengfei.nestedscrolltest.model.NestedViewModel;
import com.zhangpengfei.nestedscrolltest.model.PageVO;

import java.util.List;


@HolderAnnotation(layoutId = R.layout.item_pager)
public class PagerViewHolder extends BaseViewHolder<List<PageVO>> {

    private ViewPager viewPager;

    private TabLayout tabLayout;

    private PagerAdapter pagerAdapter;

    private List<PageVO> model;

    private NestedViewModel viewModel;

    private Observer<Integer> observer = new Observer<Integer>() {
        @Override
        public void onChanged(Integer height) {
            if (height != null) {
                itemView.getLayoutParams().height = height;
                itemView.requestLayout();
            }
        }
    };

    public PagerViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void initViews() {
        viewPager = itemView.findViewById(R.id.viewpager);
        tabLayout = itemView.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager, true);
        viewPager.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                viewPager.requestLayout();
            }

            @Override
            public void onViewDetachedFromWindow(View v) {

            }
        });

        itemView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View view) {
                if (viewModel != null) {
                    viewModel.getChildView().setValue(itemView);
                }
            }

            @Override
            public void onViewDetachedFromWindow(View view) {
                if (viewModel != null) {
                    viewModel.getChildView().setValue(null);
                }
            }
        });
    }

    @Override
    public void bindView(List<PageVO> data) {
        if (model == data) {
            return;
        }
        model = data;
        Context context = itemView.getContext();
        if (context instanceof FragmentActivity) {
            FragmentActivity fragmentActivity = (FragmentActivity) context;
            pagerAdapter = new SubPagerAdapter(fragmentActivity.getSupportFragmentManager(), model);
            viewPager.setAdapter(pagerAdapter);
            pagerAdapter.notifyDataSetChanged();
            viewModel = ViewModelProviders.of(fragmentActivity).get(NestedViewModel.class);
            viewModel.getPagerHeight().removeObserver(observer);
            viewModel.getPagerHeight().observe(fragmentActivity, observer);
            if (viewModel.getPagerHeight().getValue() != null)
                itemView.getLayoutParams().height = viewModel.getPagerHeight().getValue();
        }
    }
}
