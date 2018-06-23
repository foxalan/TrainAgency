package com.example.ec.main.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.core.delegate.bottom.BottomItemDelegate;
import com.example.ec.R;
import com.example.ec.main.personal.list.ListAdapter;
import com.example.ec.main.personal.list.ListBean;
import com.example.ec.main.personal.list.ListItemType;
import com.example.ui.recycler.BaseDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alan
 *         Date  2018/6/5.
 *         Function :
 *         Issue :
 */

public class PersonalDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_own;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        final RecyclerView rvSettings = $(R.id.rv_personal_setting);

        final ListBean  my = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_AVATAR)
                .setId(0)
                .build();

        final ListBean blank = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_BLANK)
                .setId(1)
                .build();


        final ListBean notice = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setImageUrl(R.mipmap.ic_personal_notice)
                .setValue("我的关注")
                .build();

        final ListBean say = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setImageUrl(R.mipmap.ic_personal_say)
                .setId(3)
                .setValue("我的评论")
                .build();

        final ListBean  footer = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(4)
                .setImageUrl(R.mipmap.ic_personal_footer)
                .setValue("足    迹")
                .build();

        final ListBean resource = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(5)
                .setImageUrl(R.mipmap.ic_personal_resource)
                .setValue("资源管理")
                .build();

        final ListBean blank1 = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_BLANK)
                .setId(1)
                .build();

        final ListBean be = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(6)
                .setImageUrl(R.mipmap.ic_personal_be)
                .setValue("申请成为老师/机构")
                .build();

        final ListBean hlep = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(5)
                .setImageUrl(R.mipmap.ic_personal_help)
                .setValue("帮助/反馈")
                .build();

        final ListBean setting = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(6)
                .setImageUrl(R.mipmap.ic_personal_setting)
                .setValue("设置")
                .build();




        final List<ListBean> data = new ArrayList<>();
        data.add(my);
        data.add(blank);

        data.add(notice);
        data.add(say);
        data.add(footer);
        data.add(resource);
        data.add(blank1);
        data.add(be);
        data.add(hlep);
        data.add(setting);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        rvSettings.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 2));
        rvSettings.setAdapter(adapter);
    //    rvSettings.addOnItemTouchListener(new DiscoverClickListener(this));
    }
}
