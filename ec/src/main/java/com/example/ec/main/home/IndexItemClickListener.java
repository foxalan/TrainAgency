package com.example.ec.main.home;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.core.delegate.LatteDelegate;
import com.example.ec.main.home.experience.ExperienceClassDelegate;
import com.example.ec.main.home.organization.OrganizationDelegate;
import com.example.ec.main.home.subject.SubjectDelegate;
import com.example.ui.recycler.ItemType;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItemEntity;

/**
 * 首页点击
 *
 * @author alan
 */

public class IndexItemClickListener extends SimpleClickListener {

    private final LatteDelegate DELEGATE;

    private IndexItemClickListener(LatteDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static SimpleClickListener create(LatteDelegate delegate) {
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        switch (entity.getItemType()) {
            case ItemType.TEXT_IMAGE:
                int id = entity.getField(MultipleFields.ID);
                String type = entity.getField(MultipleFields.TEXT);
                SubjectDelegate subjectDelegate = SubjectDelegate.create(id, type);
                DELEGATE.getSupportDelegate().start(subjectDelegate);
                break;
            case ItemType.RECOMMEND:
                int goodClassId = entity.getField(MultipleFields.ID);
                DELEGATE.getSupportDelegate().start(OrganizationDelegate.create(goodClassId, null));
                break;
            case ItemType.CLASS_ITEM:
                int classId = entity.getField(MultipleFields.ID);
                DELEGATE.getSupportDelegate().start(OrganizationDelegate.create(classId, null));
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
