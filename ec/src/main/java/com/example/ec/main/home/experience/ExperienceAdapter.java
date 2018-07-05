package com.example.ec.main.home.experience;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ec.R;
import com.example.ec.main.home.subject.SubjectType;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItemEntity;
import com.example.ui.recycler.MultipleRecyclerAdapter;
import com.example.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * @author alan
 *         Date  2018/7/5.
 *         Function :
 *         Issue :
 */

public class ExperienceAdapter extends MultipleRecyclerAdapter {

    protected ExperienceAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(SubjectType.SUBJECT_ITEM, R.layout.item_multiple_class_detail_exp);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {

        switch (holder.getItemViewType()) {

            case SubjectType.SUBJECT_ITEM:

                String urlImg = entity.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext).load(urlImg).apply(RECYCLER_OPTIONS).into((ImageView) holder.getView(R.id.class_icon));

                String className = entity.getField(MultipleFields.CLASSNAME);
                String courseIntro = entity.getField(MultipleFields.CLASSINFO);
                String site = entity.getField(MultipleFields.ADDRESS);
                String ageGroup = entity.getField(MultipleFields.AGE);
                double distance = entity.getField(MultipleFields.DISTANCE);

                holder.setText(R.id.class_name, className);
                holder.setText(R.id.class_des, courseIntro);
                holder.setText(R.id.class_address, site);
                holder.setText(R.id.class_age, ageGroup);
                holder.setText(R.id.class_distance, distance + "m");

                break;
            default:
                break;
        }

    }
}
