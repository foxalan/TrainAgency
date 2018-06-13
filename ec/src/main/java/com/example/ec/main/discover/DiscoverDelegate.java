package com.example.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.core.delegate.bottom.BottomItemDelegate;
import com.example.ec.R;
import com.example.ec.main.personal.PersonalClickListener;
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

public class DiscoverDelegate extends BottomItemDelegate {


    private RecyclerView rvDiscover;

    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        rvDiscover = rootView.findViewById(R.id.ryc_discover);
        List<ListBean> listBeanList = new ArrayList<>();
        ListBean organization = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setImageUrl(R.mipmap.ic_discover_organization)
                .setValue(String.valueOf("找机构"))
                .build();

        ListBean teacher = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setImageUrl(R.mipmap.ic_discover_teacher)
                .setValue(String.valueOf("找老师"))
                .build();

        ListBean blank1 = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_BLANK)
                .build();

        ListBean see = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setImageUrl(R.mipmap.ic_discover_see)
                .setValue(String.valueOf("看一看"))
                .build();


        ListBean message = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setImageUrl(R.mipmap.ic_discover_message)
                .setValue(String.valueOf("信息平台"))
                .build();

        ListBean blank2 = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_BLANK)
                .build();


        ListBean resouce = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setImageUrl(R.mipmap.ic_discover_resouce)
                .setValue(String.valueOf("资    源"))
                .build();

        listBeanList.add(organization);
        listBeanList.add(teacher);
        listBeanList.add(blank1);
        listBeanList.add(see);
        listBeanList.add(message);
        listBeanList.add(blank2);
        listBeanList.add(resouce);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvDiscover.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(listBeanList);
        rvDiscover.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 2));
        rvDiscover.setAdapter(adapter);
     //   rvDiscover.addOnItemTouchListener(new PersonalClickListener(this));
    }
}
