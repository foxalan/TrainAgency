package com.example.ec.main.home.organization;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bumptech.glide.Glide;
import com.example.core.app.Latte;
import com.example.ec.R;
import com.example.ui.banner.BannerCreator;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItemEntity;
import com.example.ui.recycler.MultipleRecyclerAdapter;
import com.example.ui.recycler.MultipleViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * @author alan
 *         Date  2018/7/2.
 *         Function :
 *         Issue :
 */

public class OrganizationAdapter extends MultipleRecyclerAdapter {

    private boolean mIsInitBanner = false;

    protected OrganizationAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(OrganizationType.ORGANIZATION_TYPE_BANNER, R.layout.item_home_organize_multiple_banner);
        addItemType(OrganizationType.ORGANIZATION_TYPE_CLASS, R.layout.item_home_organize_class);
        addItemType(OrganizationType.ORGANIZATION_TYPE_ADDRESS, R.layout.item_home_organize_address);
        addItemType(OrganizationType.ORGANIZATION_TYPE_CLASS_INFO, R.layout.item_home_organize_arrow_class_info);
        addItemType(OrganizationType.ORGANIZATION_TYPE_CLASS_COURSE, R.layout.item_home_organize_arrow_course);
        addItemType(OrganizationType.ORGANIZATION_TYPE_CLASS_TIME, R.layout.item_home_organize_course_time);
        addItemType(OrganizationType.ORGANIZATION_TYPE_CLASS_DISCOUNT, R.layout.item_home_organize_arrow_discount);
        addItemType(OrganizationType.ORGANIZATION_TYPE_CLASS_TEACHER, R.layout.item_home_organize_teacher);
        addItemType(OrganizationType.ORGANIZATION_TYPE_CLASS_IMAGE, R.layout.item_home_organize_arrow_photo);
        addItemType(OrganizationType.ORGANIZATION_TYPE_CLASS_IMAGE_LIST, R.layout.item_home_organize_arrow_photo_list);
        addItemType(OrganizationType.ORGANIZATION_TYPE_CLASS_MOBILE, R.layout.item_home_organize_arrow_bottom);

    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (entity.getItemType()) {
            case OrganizationType.ORGANIZATION_TYPE_BANNER:

                final ArrayList<String> bannerImages;
                if (!mIsInitBanner) {

                    bannerImages = entity.getField(MultipleFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler_item_org);
                    BannerCreator.setDefault(convenientBanner, bannerImages, this);
                    mIsInitBanner = true;
                }
                break;
            case OrganizationType.ORGANIZATION_TYPE_CLASS:
                String className = entity.getField(MultipleFields.HOME_ORGANIZATION_CLASS_NAME);
                double distance = entity.getField(MultipleFields.HOME_ORGANIZATION_CLASS_DISTANCE);
                int browse = entity.getField(MultipleFields.HOME_ORGANIZATION_CLASS_BROWSE);
                int like = entity.getField(MultipleFields.HOME_ORGANIZATION_CLASS_LIKE);
                holder.setText(R.id.tv_class_distance, "距你" + distance);
                holder.setText(R.id.tv_class_name, className);
                holder.setText(R.id.tv_class_browse, "浏览 " + browse);
                holder.setText(R.id.tv_class_like, "关注 " + like);
                break;
            case OrganizationType.ORGANIZATION_TYPE_ADDRESS:
                String address = entity.getField(MultipleFields.HOME_ORGANIZATION_CLASS_ADDRESS);
                holder.setText(R.id.tv_address_value, address);
                break;
            case OrganizationType.ORGANIZATION_TYPE_CLASS_INFO:
                String classInfo = entity.getField(MultipleFields.HOME_ORGANIZATION_CLASS_INFO);
                holder.setText(R.id.tv_class_info_value, classInfo);
                break;
            case OrganizationType.ORGANIZATION_TYPE_CLASS_COURSE:
                break;
            case OrganizationType.ORGANIZATION_TYPE_CLASS_TIME:
                String time = entity.getField(MultipleFields.HOME_ORGANIZATION_CLASS_TIME);
                holder.setText(R.id.tv_course_time, time);
                break;
            case OrganizationType.ORGANIZATION_TYPE_CLASS_DISCOUNT:
                String discount = entity.getField(MultipleFields.HOME_ORGANIZATION_CLASS_DISCOUNTS);
                holder.setText(R.id.tv_discount_value, discount);
                break;
            case OrganizationType.ORGANIZATION_TYPE_CLASS_TEACHER:
                String urlImg = entity.getField(MultipleFields.HOME_ORGANIZATION_TEACHER_IMG);
                String info = entity.getField(MultipleFields.HOME_ORGANIZATION_TEACHER_INFO);
                Log.e("organization", "info:" + info);
                Glide.with(mContext)
                        .load(urlImg)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(R.id.tv_teacher_image));
                holder.setText(R.id.tv_teacher_info_value, info);
            case OrganizationType.ORGANIZATION_TYPE_CLASS_IMAGE:

                break;
            case OrganizationType.ORGANIZATION_TYPE_CLASS_IMAGE_LIST:

                LinearLayout mGallery = holder.getView(R.id.id_gallery);
                List<String> imgUrlList = entity.getField(MultipleFields.HOME_ORGANIZATION_IMGLIST);
                for (int i = 0; i <imgUrlList.size(); i++)
                {
                    Log.e("organize","url:"+imgUrlList.get(i));
                    View view = LayoutInflater.from(mContext).inflate(R.layout.item_image, mGallery, false);
                    ImageView img = view.findViewById(R.id.iv_item);
                    Glide.with(mContext)
                            .load(imgUrlList.get(i))
                            .apply(RECYCLER_OPTIONS)
                            .into(img);
                    mGallery.addView(view);
                }
                break;
            case OrganizationType.ORGANIZATION_TYPE_CLASS_MOBILE:

                break;
            default:
                break;
        }
    }
}
