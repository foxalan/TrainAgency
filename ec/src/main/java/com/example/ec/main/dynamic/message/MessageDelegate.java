package com.example.ec.main.dynamic.message;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;
import com.example.ec.main.dynamic.message.list.MessageAdapter;
import com.example.ec.main.dynamic.message.list.MessageBean;
import com.example.ec.main.dynamic.message.list.MessageType;
import com.example.ec.main.personal.list.ListItemType;
import com.example.ui.recycler.BaseDecoration;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author alan
 *         Date  2018/6/9.
 *         Function : 消息页面，用于显示多少条消息未读
 *         Issue :
 */

public class MessageDelegate extends LatteDelegate {

    private RecyclerView mRecyclerMessage;
    private IconTextView mIcBack;

    @Override
    public Object setLayout() {
        return R.layout.delegate_dynamic_message;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

            mIcBack = rootView.findViewById(R.id.icon_back);
            mRecyclerMessage = rootView.findViewById(R.id.ryc_message);

            mIcBack.setOnClickListener(v -> getSupportDelegate().pop());
            initRecyclerView();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {

        @SuppressLint("ResourceType")
        MessageBean msgUnRead = new MessageBean.Builder()
                .setCount(5)
                .setValue(MessageType.UNREAD)
                .setImageUrl(R.mipmap.ic_home_message)
                .setItemType(ListItemType.ITEM_IMAGE_TEXT_AVATAR)
                .setText("未读消息")
                .build();

        @SuppressLint("ResourceType")
        MessageBean msgRead = new MessageBean.Builder()

                .setValue(MessageType.READ)
                .setImageUrl(R.mipmap.ic_home_message_read)
                .setItemType(ListItemType.ITEM_IMAGE_TEXT_AVATAR)
                .setText("已读消息")
                .build();
        List<MessageBean> messageBeanList = new ArrayList<>();
        messageBeanList.add(msgUnRead);
        messageBeanList.add(msgRead);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerMessage.setLayoutManager(manager);
        final MessageAdapter adapter = new MessageAdapter(messageBeanList);
        mRecyclerMessage.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 2));
        mRecyclerMessage.setAdapter(adapter);
    //    mRecyclerMessage.addOnItemTouchListener(new DynamicClickListener(this));
    }
}
