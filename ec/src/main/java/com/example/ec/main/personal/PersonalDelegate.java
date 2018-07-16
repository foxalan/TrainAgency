package com.example.ec.main.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.core.app.AccountManager;
import com.example.core.delegate.bottom.BottomItemDelegate;
import com.example.ec.R;
import com.example.ec.main.personal.help.AdviceDelegate;
import com.example.ec.main.personal.help.HelpAndAdviceDelegate;
import com.example.ec.main.personal.list.ListAdapter;
import com.example.ec.main.personal.list.ListBean;
import com.example.ec.main.personal.list.ListItemType;
import com.example.ec.main.personal.notice.NoticeDelegate;
import com.example.ec.main.personal.setting.SettingDelegate;
import com.example.ec.main.personal.userinfo.UserInfoDelegate;
import com.example.ui.recycler.BaseDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alan
 *         Date  2018/6/5.
 *         Function :
 *         Issue :
 */

public class PersonalDelegate extends BottomItemDelegate implements IRrefreshView{

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    private RecyclerView rvSettings;
    private ListAdapter adapter;
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        rvSettings = $(R.id.rv_personal_setting);

        final ListBean  my = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_USER_AVATAR)
                .setUrl(AccountManager.isSignIn()?AccountManager.getUserIcon():"")
                .setText(AccountManager.isSignIn()?AccountManager.getUserType():"")
                .setValue(AccountManager.isSignIn()?AccountManager.getUserSign():"")
                .setId(0)
                .build();

        Log.e("accountmanager",AccountManager.getUserSign()+"==========");

        final ListBean blank = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_BLANK)
                .setId(1)
                .build();


        final ListBean notice = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_IMAGE_TEXT_AVATAR)
                .setId(2)
                .setImageUrl(R.mipmap.ic_personal_notice)
                .setDelegate(new NoticeDelegate())
                .setValue("我的关注")
                .build();

        final ListBean say = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_IMAGE_TEXT_AVATAR)
                .setImageUrl(R.mipmap.ic_personal_say)
                .setId(3)
                .setValue("我的评论")
                .build();

        final ListBean  footer = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_IMAGE_TEXT_AVATAR)
                .setId(4)
                .setImageUrl(R.mipmap.ic_personal_footer)
                .setValue("足    迹")
                .build();

        final ListBean resource = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_IMAGE_TEXT_AVATAR)
                .setId(5)
                .setImageUrl(R.mipmap.ic_personal_resource)
                .setValue("资源管理")
                .build();

        final ListBean blank1 = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_BLANK)
                .setId(100)
                .build();

        final ListBean be = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_IMAGE_TEXT_AVATAR)
                .setId(6)
                .setImageUrl(R.mipmap.ic_personal_be)
                .setValue("申请成为老师/机构")
                .build();

        final ListBean hlpe = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_IMAGE_TEXT_AVATAR)
                .setId(7)
                .setImageUrl(R.mipmap.ic_personal_help)
                .setValue("帮助/反馈")
                .setDelegate(new HelpAndAdviceDelegate())
                .build();

        final ListBean setting = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_IMAGE_TEXT_AVATAR)
                .setId(8)
                .setDelegate(new SettingDelegate())
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
        data.add(hlpe);
        data.add(setting);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvSettings.setLayoutManager(manager);
        adapter = new ListAdapter(data);
        rvSettings.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(),R.color.app_background),2));
        rvSettings.setAdapter(adapter);
        rvSettings.addOnItemTouchListener(new PersonalClickListener(this,this));
    }

    @Override
    public void updateIcon(String url) {

        Log.e("alan","update icon");

        ListBean bean = adapter.getData().get(0);
        bean.setmUrl(url);

        adapter.notifyItemChanged(0);

    }

    @Override
    public void updateAccount(String account) {

    }

    @Override
    public void updateSign(String sign) {

    }
}
