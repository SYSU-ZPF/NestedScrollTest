package com.zhangpengfei.nestedscrolltest.items;

import com.zhangpengfei.nestedscrolltest.ViewType;
import com.zhangpengfei.nestedscrolltest.common.AdapterItem;
import com.zhangpengfei.nestedscrolltest.model.PageVO;

import java.util.List;


public class PageItem implements AdapterItem<List<PageVO>> {

    private List<PageVO> model;

    public PageItem(List<PageVO> model) {
        this.model = model;
    }

    @Override
    public List<PageVO> getDataModel() {
        return model;
    }

    @Override
    public int getViewType() {
        return ViewType.TYPE_PAGER;
    }
}
