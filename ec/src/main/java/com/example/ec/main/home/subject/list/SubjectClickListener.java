package com.example.ec.main.home.subject.list;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.ec.main.home.organization.OrganizationDelegate;
import com.example.ec.main.home.subject.SubjectDelegate;
import com.example.ec.main.home.subject.SubjectType;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItemEntity;

/**
 * @author alan
 *         Date  2018/6/30.
 *         Function :
 *         Issue :
 */

public class SubjectClickListener extends SimpleClickListener {

    private final SubjectListDelegate DELEGATE;

    public SubjectClickListener(SubjectListDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        switch (entity.getItemType()) {
            case SubjectType.SUBJECT_ITEM:
                int id = entity.getField(MultipleFields.ID);
                OrganizationDelegate organizationDelegate = OrganizationDelegate.create(id,null);
                DELEGATE.getSupportDelegate().start(organizationDelegate);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
