package com.example.ec.main.personal.userinfo;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.core.app.AccountManager;
import com.example.core.app.Latte;
import com.example.core.delegate.LatteDelegate;
import com.example.core.net.RestClient;
import com.example.core.net.callback.ISuccess;
import com.example.core.util.callback.CallbackManager;
import com.example.core.util.callback.CallbackType;
import com.example.core.util.callback.IGlobalCallback;
import com.example.ec.R;
import com.example.ec.main.personal.IRrefreshView;
import com.example.ec.main.personal.list.ListAdapter;
import com.example.ec.main.personal.list.ListBean;
import com.example.ec.main.personal.list.ListItemType;
import com.example.ui.recycler.BaseDecoration;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author alan
 *         Date  2018/7/4.
 *         Function : 帐户信息页
 *         Issue :
 */

@SuppressLint("ValidFragment")
public class UserInfoDelegate extends LatteDelegate {

    private RecyclerView mRycUserInfo;
    private ListAdapter adapter;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal_user_info;
    }


    private IRrefreshView refreshView;

    public UserInfoDelegate(){}

    @SuppressLint("ValidFragment")
    public UserInfoDelegate(IRrefreshView rrefreshView) {
        this.refreshView = rrefreshView;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mRycUserInfo = rootView.findViewById(R.id.rv_user_info);

        ListBean icon = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TEXT_IMG_AVATAR)
                .setId(0)
                .setUrl(AccountManager.getUserIcon())
                .build();

        final ListBean username = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TEXT_TEXT)
                .setId(1)
                .setText("用户名")
                .setValue(AccountManager.getUserType())
                .build();


        final ListBean sign = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_TEXT_TEXT)
                .setId(2)
                .setText("个性签名")
                .setImageUrl(R.mipmap.ic_personal_notice)
                .setValue(AccountManager.getUserSign())
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

        data.add(icon);
        data.add(username);
        data.add(sign);
        data.add(blank1);
        data.add(phone);
        data.add(resource);


        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRycUserInfo.setLayoutManager(manager);
        adapter = new ListAdapter(data);
        mRycUserInfo.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 2));
        mRycUserInfo.setAdapter(adapter);
        mRycUserInfo.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
                int id = bean.getId();
                switch (id) {
                    case 0:
                        //开始照相机或选择图片
                        CallbackManager.getInstance()
                                .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                                    @Override
                                    public void executeCallback(Uri args) {

                                        final ImageView avatar = view.findViewById(R.id.tv_arrow_image);
                                        Glide.with(getContext())
                                                .load(args)
                                                .into(avatar);

                                        Log.e("alan", args.getPath());


                                        RestClient.builder()
                                                .url("http://192.168.1.186/Update/updateuserinfo")
                                                .params("ID", AccountManager.getUserId())
                                                .loader(getContext())
                                                .file(args.getPath())
                                                .success(response -> {
                                                    final JSONObject object = JSON.parseObject(response);
                                                    String msg = object.getString("msg");
                                                    Latte.getHandler().post(() -> Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show());
                                                    Log.e("alan", "msg" + msg);
                                                    if ("更改头像成功！".equals(msg)) {
                                                        AccountManager.setUserIcon(args.getPath());
                                                        refreshView.updateIcon(args.getPath());
                                                    }
                                                })
                                                .build()
                                                .upload();
                                    }
                                });
                        startCameraWithCheck();
                        break;
                    case 1:
                        getSupportDelegate().start(new UserInfoAccountDelegate());
                        break;
                    case 2:

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

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultVerticalAnimator();
    }

    @Override
    public void onNewBundle(Bundle args) {
        super.onNewBundle(args);

        if (getArguments() != null) {
            Bundle bundle = getArguments();

            String msg = bundle.getString("update");
            Log.e("alan","msg========="+msg+"bundle"+bundle.toString());

                adapter.getData().get(1).setmValue(AccountManager.getUserType());
                adapter.notifyItemChanged(1);
                refreshView.updateAccount("");
        }
    }
}
