package com.example.ec.main.home.subject;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ec.R;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItemEntity;
import com.example.ui.recycler.MultipleRecyclerAdapter;
import com.example.ui.recycler.MultipleViewHolder;

import java.util.List;


import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author alan
 *         Date  2018/6/30.
 *         Function :
 *         Issue :
 */

public class SubjectAdapter extends MultipleRecyclerAdapter {

    public SubjectAdapter(List<MultipleItemEntity> data) {
        super(data);

        addItemType(SubjectType.SUBJECT_TYPE, R.layout.item_home_subject_type);

    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case SubjectType.SUBJECT_TYPE:
                String text = entity.getField(MultipleFields.TEXT);
                String url = entity.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext).load(url).apply(RECYCLER_OPTIONS).into((ImageView) holder.getView(R.id.sub_img_multiple));
                holder.setText(R.id.sub_tv_multiple,text);
                break;
            default:
                break;
        }
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }
}
