package com.example.ec.main.personal.notice.detail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

    private IUnNoticeListener unNoticeListener;

    public NoticeAdapter(Context mContext, List<NoticeBean> mData, int resID) {
        super(mContext, mData, resID);
    }

    public void setUnNoticeListener(IUnNoticeListener unNoticeListener) {
        this.unNoticeListener = unNoticeListener;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void convert(CustomViewHolder viewHolder, int position) {

        LinearLayout linearLayout = viewHolder.getView(R.id.ll_item);

        ImageView icon = viewHolder.getView(R.id.iv_notice_teacher_icon);
        AppCompatTextView textView = viewHolder.getView(R.id.tv_notice_teacher_name);
        Glide.with(mContext).load(mData.get(position).getImgUrl()).into(icon);
        textView.setText(mData.get(position).getName());

        Button mBtnUnNotice = viewHolder.getView(R.id.btn_un_notice);
        mBtnUnNotice.setOnClickListener(v -> {

            if (unNoticeListener != null) {
                unNoticeListener.unNotice(mData.get(position).getType(), position, v);
            }

        });

        linearLayout.setOnClickListener(v -> {
            if (unNoticeListener != null) {
                unNoticeListener.toItem(mData.get(position).getType(), position, v);
            }
        });


    }
}
