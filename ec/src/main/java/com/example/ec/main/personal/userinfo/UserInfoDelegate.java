package com.example.ec.main.personal.userinfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;
import com.example.ec.main.personal.list.ListAdapter;
import com.example.ec.main.personal.list.ListBean;
import com.example.ec.main.personal.list.ListItemType;
import com.example.ui.recycler.BaseDecoration;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author alan
 *         Date  2018/7/4.
 *         Function : 帐户信息页
 *         Issue :
 */

public class UserInfoDelegate extends LatteDelegate {

    private RecyclerView mRycUserInfo;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal_user_info;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mRycUserInfo = rootView.findViewById(R.id.rv_user_info);

        final ListBean username = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TEXT_TEXT)
                .setId(1)
                .setText("用户名")
                .setValue("一切====")
                .build();


        final ListBean sign = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TEXT_TEXT)
                .setId(2)
                .setText("个性签名")
                .setImageUrl(R.mipmap.ic_personal_notice)
                .setValue("123发展属性")
                .build();

        final ListBean blank1 = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_BLANK)
                .setId(3)
                .build();

        final ListBean phone = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_IMAGE_TEXT_AVATAR)
                .setImageUrl(R.mipmap.ic_personal_say)
                .setId(5)
                .setValue("手机")
                .build();


        final ListBean resource = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_IMAGE_TEXT_AVATAR)
                .setId(6)
                .setImageUrl(R.mipmap.ic_personal_resource)
                .setValue("微信")
                .build();

        final List<ListBean> data = new ArrayList<>();

        data.add(username);
        data.add(sign);

        data.add(blank1);
        data.add(phone);
        data.add(resource);


        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRycUserInfo.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRycUserInfo.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 2));
        mRycUserInfo.setAdapter(adapter);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
