package com.example.ec.main.personal.notice.detail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.core.adapter.CustomAdapter;
import com.example.core.adapter.CustomViewHolder;
import com.example.ec.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.ui.recycler.MultipleRecyclerAdapter.RECYCLER_OPTIONS;

/**
 * @author alan
 *         Date  2018/7/5.
 *         Function :
 *         Issue :
 */

public class NoticeAdapter extends CustomAdapter<NoticeBean> {

    public NoticeAdapter(Context mContext, List<NoticeBean> mData, int resID) {
        super(mContext, mData, resID);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void convert(CustomViewHolder viewHolder, int position) {

        ImageView icon = viewHolder.getView(R.id.iv_notice_teacher_icon);
        AppCompatTextView textView = viewHolder.getView(R.id.tv_notice_teacher_name);
        Glide.with(mContext).load(mData.get(position).getImgUrl()).apply(RECYCLER_OPTIONS).into(icon);
        textView.setText(mData.get(position).getName()+":"+mData.get(position).getPhone());
    }
}
