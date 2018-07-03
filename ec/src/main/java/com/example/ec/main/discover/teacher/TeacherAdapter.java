package com.example.ec.main.discover.teacher;

import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bumptech.glide.Glide;
import com.example.ec.R;
import com.example.ec.main.discover.DisCoverItemType;
import com.example.ui.banner.BannerCreator;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItemEntity;
import com.example.ui.recycler.MultipleRecyclerAdapter;
import com.example.ui.recycler.MultipleViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alan
 *         Date  2018/7/3.
 *         Function :
 *         Issue :
 */

public class TeacherAdapter extends MultipleRecyclerAdapter {

    private boolean mIsInitBanner = false;

    protected TeacherAdapter(List<MultipleItemEntity> data) {
        super(data);

        addItemType(DisCoverItemType.DISCOVER_TEACHER_TYPE_BANNER, R.layout.item_home_organize_multiple_banner);
        addItemType(DisCoverItemType.DISCOVER_TEACHER_TYPE_INFO, R.layout.item_discover_teacher);
    }


    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);

        switch (entity.getItemType()) {
            case DisCoverItemType.DISCOVER_TEACHER_TYPE_BANNER:
                final ArrayList<String> bannerImages;
                if (!mIsInitBanner) {

                    bannerImages = entity.getField(MultipleFields.DISCOVER_TEACHER_BANNERS);
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler_item_org);
                    BannerCreator.setDefault(convenientBanner, bannerImages, this);
                    mIsInitBanner = true;
                }
                break;
            case DisCoverItemType.DISCOVER_TEACHER_TYPE_INFO:

                String urlImg = entity.getField(MultipleFields.DISCOVER_TEACHER_IMG);
                String name = entity.getField(MultipleFields.DISCOVER_TEACHER_NAME);
                String info = entity.getField(MultipleFields.DISVOCER_TEACHER_INFO);
                String className = entity.getField(MultipleFields.DISVOCER_TEACHER_CLASS);
                String mobile = entity.getField(MultipleFields.DISVOCER_TEACHER_MOBILE);

                Glide.with(mContext)
                        .load(urlImg)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(R.id.iv_teacher));
                holder.setText(R.id.tv_teacher_name,name);
                holder.setText(R.id.tv_teacher_info,info);
                holder.setText(R.id.tv_teacher_address,className);
                holder.setText(R.id.tv_teacher_phone,mobile);
                break;
            default:
                break;
        }


    }


}
