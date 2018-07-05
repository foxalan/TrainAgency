package com.example.ec.main.personal.notice.detail;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;


import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;
import com.example.ec.detail.ImageDelegate;
import com.example.ec.main.personal.list.ListBean;


import java.util.ArrayList;
import java.util.List;

import q.rorbin.badgeview.DisplayUtil;

import static com.blankj.utilcode.util.ConvertUtils.dp2px;


/**
 * @author alan
 *         Date  2018/7/4.
 *         Function : 关注列表界面
 *         Issue :
 */

public class NoticeDetailDelegate extends LatteDelegate {

    private static final String NOTICE_TYPE = "ARG_PICTURES";

    private SwipeMenuListView listView;
    private NoticeAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static NoticeDetailDelegate create(String type) {

        final Bundle args = new Bundle();
        Log.e("notice","type:before"+type);
        args.putString(NOTICE_TYPE, type);
        final NoticeDetailDelegate delegate = new NoticeDetailDelegate();
        delegate.setArguments(args);
        return delegate;

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_listview;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {


        listView = rootView.findViewById(R.id.lv_notice);

        initImages();

        listView.setAdapter(adapter);

        SwipeMenuCreator creator = menu -> {
            // create "open" item
            SwipeMenuItem openItem = new SwipeMenuItem(getContext());
            // set item background
            openItem.setWidth(DisplayUtil.dp2px(getContext(),90));
            openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
            // set item width
            // set item title
            openItem.setTitle("Open");
            openItem.setTitleSize(18);
            // set item title font color
            openItem.setTitleColor(Color.WHITE);
            // add to menu
            menu.addMenuItem(openItem);

            // create "delete" item
            SwipeMenuItem deleteItem = new SwipeMenuItem(getContext());
            // set item background
            deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
            // set item width
            // set a icon
            deleteItem.setTitle("删除");
            // add to menu
            deleteItem.setWidth(dp2px(90));
            menu.addMenuItem(deleteItem);
        };

        listView.setMenuCreator(creator);
        // Right
        listView.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);
        listView.setOnMenuItemClickListener((position, menu, index) -> {
            switch (index) {
                case 0:

                    break;
                case 1:

                    break;
                default:
                    break;
            }
            return false;
        });



    }

    private void initImages() {
        final Bundle arguments = getArguments();
        if (arguments != null) {

            final String type = arguments.getString(NOTICE_TYPE);
            Log.e("notice","type:"+type);
            List<NoticeBean> noticeBeanList = new ArrayList<>();
            switch (type) {
                case NoticeType.NOTICE_ALL:
                    for (int i = 0;i<2;i++){
                        NoticeBean noticeBean = new NoticeBean("12","","all"+i,"152"+i,true);
                        noticeBeanList.add(noticeBean);
                    }
                    break;
                case NoticeType.NOTICE_CURRENT:
                    for (int i = 0;i<5;i++){
                        NoticeBean noticeBean = new NoticeBean("12","","current"+i,"152"+i,true);
                        noticeBeanList.add(noticeBean);
                    }
                    break;
                default:
                    break;
            }


            adapter = new NoticeAdapter(getContext(), noticeBeanList, R.layout.item_personal_notice);
        }
    }

}
