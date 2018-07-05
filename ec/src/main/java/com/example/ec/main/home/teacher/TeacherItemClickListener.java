package com.example.ec.main.home.teacher;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.ec.main.home.subject.SubjectDelegate;
import com.example.ec.main.home.subject.SubjectType;

import com.example.ec.main.home.teacher.detail.TeacherDetailDelegate;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItemEntity;

import static com.example.ec.main.discover.DisCoverItemType.DISCOVER_TEACHER_TYPE_INFO;

/**
 * @author alan
 *         Date  2018/6/30.
 *         Function :
 *         Issue :
 */

public class TeacherItemClickListener extends SimpleClickListener {

    private final TeacherDelegate DELEGATE;

    public TeacherItemClickListener(TeacherDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        switch (entity.getItemType()) {
            case DISCOVER_TEACHER_TYPE_INFO:
                int id = entity.getField(MultipleFields.ID);
                TeacherDetailDelegate teacherDelegate = TeacherDetailDelegate.create(id);
                DELEGATE.getSupportDelegate().start(teacherDelegate);
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
