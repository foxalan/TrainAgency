package com.example.ec.main.personal.notice.detail;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;


import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;
import com.example.ec.detail.ImageDelegate;


import java.util.ArrayList;

import static com.blankj.utilcode.util.ConvertUtils.dp2px;

/**
 * @author alan
 *         Date  2018/7/4.
 *         Function : 关注列表界面
 *         Issue :
 */

public class NoticeDetailDelegate extends LatteDelegate {

    private static final String ARG_PICTURES = "ARG_PICTURES";

    private SwipeMenuListView listView;

    public static NoticeDetailDelegate create(ArrayList<NoticeBean> noticeBeans) {

        final Bundle args = new Bundle();
        args.putSerializable(ARG_PICTURES, noticeBeans);
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
        SwipeMenuCreator creator = menu -> {
            // create "open" item
            SwipeMenuItem openItem = new SwipeMenuItem(getContext());
            // set item background
            openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
            // set item width
            openItem.setWidth(dp2px(90));
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
            deleteItem.setWidth(dp2px(90));
            // set a icon
            deleteItem.setTitle("删除");
            // add to menu
            menu.addMenuItem(deleteItem);
        };

        listView.setMenuCreator(creator);
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


        initImages();
    }

    private void initImages() {
        final Bundle arguments = getArguments();
        if (arguments != null) {
            final ArrayList<NoticeBean> pictures = (ArrayList<NoticeBean>) arguments.getSerializable(ARG_PICTURES);


        }
    }

}
