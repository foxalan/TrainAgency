package com.example.ec.main.discover.organization;

import com.example.ui.recycler.MultipleItemEntity;
import com.example.ui.recycler.MultipleRecyclerAdapter;
import com.example.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * @author alan
 *         Date  2018/7/3.
 *         Function :
 *         Issue :
 */

public class OrganizationAdapter  extends MultipleRecyclerAdapter{

    protected OrganizationAdapter(List<MultipleItemEntity> data) {
        super(data);

    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);

    }
}
