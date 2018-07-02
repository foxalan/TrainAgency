package com.example.ec.main.home.organization;

import com.example.ui.recycler.MultipleItemEntity;
import com.example.ui.recycler.MultipleRecyclerAdapter;
import com.example.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * @author alan
 *         Date  2018/7/2.
 *         Function :
 *         Issue :
 */

public class OrganizationAdapter extends MultipleRecyclerAdapter {

    protected OrganizationAdapter(List<MultipleItemEntity> data) {
        super(data);

    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (entity.getItemType()) {
            case OrganizationType.ORGANIZATION_TYPE_CLASS:

                break;
            default:
                break;
        }
    }
}
