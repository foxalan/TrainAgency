package com.example.ec.main.personal.help;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;
import com.example.ec.main.personal.list.ListAdapter;
import com.example.ec.main.personal.list.ListBean;
import com.example.ec.main.personal.list.ListItemType;
import com.example.ec.main.personal.notice.NoticeDelegate;
import com.example.ui.recycler.BaseDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alan
 *         Date  2018/7/9.
 *         Function : 帮助与反馈
 *         Issue :
 */

public class HelpAndAdviceDelegate extends LatteDelegate {

    private RecyclerView mRycHelp;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal_help_advice;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mRycHelp = rootView.findViewById(R.id.ryc_help_advice);

        final ListBean notice = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TEXT_AVATAR)
                .setId(1)
                .setDelegate(new NoticeDelegate())
                .setValue("问题帮助")
                .build();

        final ListBean say = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TEXT_AVATAR)
                .setId(2)
                .setValue("意见反馈")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(notice);
        data.add(say);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRycHelp.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRycHelp.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 2));
        mRycHelp.setAdapter(adapter);
        mRycHelp.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        getSupportDelegate().start(new HelpDelegate());
                        break;
                    case 1:
                        getSupportDelegate().start(new AdviceDelegate());
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
        });
    }
}
