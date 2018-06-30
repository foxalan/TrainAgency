package com.example.ec.main.home.subject;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.ec.main.home.subject.list.SubjectListDelegate;
import com.example.ui.recycler.ItemType;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItemEntity;

/**
 * @author alan
 *         Date  2018/6/30.
 *         Function :
 *         Issue :
 */

public class SubjectItemClickListener extends SimpleClickListener {

    private final SubjectDelegate DELEGATE;

    public SubjectItemClickListener(SubjectDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        switch (entity.getItemType()) {
            case SubjectType.SUBJECT_TYPE:
                int id = entity.getField(MultipleFields.ID);
                String type = entity.getField(MultipleFields.TEXT);
                SubjectListDelegate subjectDelegate = SubjectListDelegate.create(id,type);
                DELEGATE.getSupportDelegate().start(subjectDelegate);
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
