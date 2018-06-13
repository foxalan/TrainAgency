package com.example.ec.main.dynamic;

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

public class DynamicDelegate extends BottomItemDelegate {

    private RecyclerView rvDynamic;

    @Override
    public Object setLayout() {
        return R.layout.delegate_dynamic;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        rvDynamic = rootView.findViewById(R.id.ryc_dynamic);

        List<ListBean> listBeanList = new ArrayList<>();

        ListBean news = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setImageUrl(R.mipmap.ic_dynamic_news)
                .setValue(String.valueOf("新闻"))
                .build();

        ListBean knowledge = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setImageUrl(R.mipmap.ic_dynamic_knowleage)
                .setValue(String.valueOf("宝典"))
                .build();

        ListBean blank1 = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_BLANK)
                .build();

        ListBean log = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setImageUrl(R.mipmap.ic_dynamic_daily)
                .setValue(String.valueOf("发表日志"))
                .build();


        ListBean message = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setImageUrl(R.mipmap.ic_dynamic_message)
                .setValue(String.valueOf("消息"))
                .build();

        ListBean blank2 = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_BLANK)
                .build();


        ListBean  campaign = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setImageUrl(R.mipmap.ic_dynamic_activity)
                .setValue(String.valueOf("活动"))
                .build();

        listBeanList.add(news);
        listBeanList.add(knowledge);
        listBeanList.add(blank1);
        listBeanList.add(log);
        listBeanList.add(message);
        listBeanList.add(blank2);
        listBeanList.add(campaign);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvDynamic.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(listBeanList);
        rvDynamic.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 2));
        rvDynamic.setAdapter(adapter);
    }
}
