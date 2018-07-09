package com.example.ec.main.personal.setting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.core.app.AccountManager;
import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;
import com.example.ec.main.personal.follow.FollowDelegate;
import com.example.ec.main.personal.list.ListAdapter;
import com.example.ec.main.personal.list.ListBean;
import com.example.ec.main.personal.list.ListItemType;
import com.example.ec.main.personal.setting.psd.ModifyDelegate;
import com.example.ec.main.personal.userinfo.UserInfoDelegate;
import com.example.ec.sign.SignInPsdDelegate;
import com.example.ui.recycler.BaseDecoration;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author alan
 *         Date  2018/7/4.
 *         Function : 设置页面
 *         Issue :
 */

public class SettingDelegate extends LatteDelegate {

    private RecyclerView mRycSettings;
    private AppCompatTextView mTvLoginOut;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal_setting;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        mRycSettings = rootView.findViewById(R.id.ryc_setting);
        mTvLoginOut = rootView.findViewById(R.id.tv_login_out);

        final ListBean one = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TEXT_AVATAR)
                .setId(1)
                .setValue("帐号管理")
                .build();


        final ListBean notice = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TEXT_AVATAR)
                .setId(2)
                .setValue("设置密码")
                .build();


        final ListBean say = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TEXT_AVATAR)
                .setId(3)
                .setValue("修改密码")
                .build();

        final ListBean blank = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_BLANK)
                .build();

        final ListBean footer = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TEXT_AVATAR)
                .setId(4)
                .setValue("隐私")
                .build();

        final ListBean resource = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TEXT_AVATAR)
                .setId(5)
                .setValue("通用")
                .build();


        final ListBean about = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TEXT_AVATAR)
                .setId(6)
                .setValue("关于")
                .build();


        final List<ListBean> data = new ArrayList<>();

        data.add(one);
        data.add(notice);
        data.add(say);
        data.add(blank);
        data.add(footer);
        data.add(resource);
        data.add(about);


        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRycSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRycSettings.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 2));
        mRycSettings.setAdapter(adapter);
        mRycSettings.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 2:
                        getSupportDelegate().start(new ModifyDelegate());
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

        mTvLoginOut.setOnClickListener(v -> {
            AccountManager.setSignState(false);
            getSupportDelegate().startWithPop(new SignInPsdDelegate());
        });
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();

    }
}
