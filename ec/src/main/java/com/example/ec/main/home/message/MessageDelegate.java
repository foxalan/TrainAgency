package com.example.ec.main.home.message;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;
import com.example.ec.main.home.message.list.MessageAdapter;
import com.example.ec.main.home.message.list.MessageBean;
import com.example.ec.main.personal.PersonalClickListener;
import com.example.ec.main.personal.list.ListItemType;
import com.example.ui.recycler.BaseDecoration;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alan
 *         Date  2018/6/9.
 *         Function :
 *         Issue :
 */

public class MessageDelegate extends LatteDelegate {

    private RecyclerView mRecyclerMessage;
    private IconTextView mIcBack;

    @Override
    public Object setLayout() {
        return R.layout.delegate_home_message;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

            mIcBack = rootView.findViewById(R.id.icon_back);
            mRecyclerMessage = rootView.findViewById(R.id.ryc_message);

            mIcBack.setOnClickListener(v -> getSupportDelegate().pop());
            initRecyclerView();
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {

        MessageBean msgUnRead = new MessageBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setText("未读消息")
                .build();

        MessageBean msgRead = new MessageBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
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
        mRecyclerMessage.addOnItemTouchListener(new PersonalClickListener(this));
    }
}
