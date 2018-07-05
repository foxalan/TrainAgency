package com.example.ui.recycler;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.ui.R;
import com.example.ui.banner.BannerCreator;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 傅令杰
 */

public class MultipleRecyclerAdapter extends
        BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>
        implements
        BaseQuickAdapter.SpanSizeLookup,
        OnItemClickListener {

    private IChoicenessClickListener choicenessClickListener;

    public IChoicenessClickListener getChoicenessClickListener() {
        return choicenessClickListener;
    }

    public void setChoicenessClickListener(IChoicenessClickListener choicenessClickListener) {
        this.choicenessClickListener = choicenessClickListener;
    }

    /**
     * 确保初始化一次Banner，防止重复Item加载
     */
    private boolean mIsInitBanner = false;
    /**
     * 设置图片加载策略
     */
    public static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    protected MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    public static MultipleRecyclerAdapter create(List<MultipleItemEntity> data) {
        return new MultipleRecyclerAdapter(data);
    }

    public static MultipleRecyclerAdapter create(DataConverter converter) {
        return new MultipleRecyclerAdapter(converter.convert());
    }

    public void refresh(List<MultipleItemEntity> data) {
        getData().clear();
        setNewData(data);
        notifyDataSetChanged();
    }

    private void init() {
        //设置不同的item布局

        addItemType(ItemType.TEXT_HEADER, R.layout.item_multiple_image);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_image_text);
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);
        addItemType(ItemType.RECOMMEND, R.layout.arrow_item_organization);
        addItemType(ItemType.CHOICENESS, R.layout.arrow_item_teacher);
        addItemType(ItemType.CLASS_ITEM, R.layout.item_multiple_class_detail);
        //设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);
    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        final String text;
        final String imageUrl;
        final ArrayList<String> bannerImages;
        final String orgImg;
        final String orgImgInfo;
        final String classInfo;
        final String className;
        final String phone;
        final String address;

        switch (holder.getItemViewType()) {
            case ItemType.TEXT_HEADER:

                break;
            case ItemType.TEXT_IMAGE:
                text = entity.getField(MultipleFields.TEXT);
                imageUrl = entity.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) holder.getView(R.id.img_multiple));
                holder.setText(R.id.tv_multiple, text);
                break;
            case ItemType.BANNER:
                if (!mIsInitBanner) {
                    bannerImages = entity.getField(MultipleFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_recycler_item);
                    BannerCreator.setDefault(convenientBanner, bannerImages, this);
                    mIsInitBanner = true;
                }
                break;
            case ItemType.RECOMMEND:

                orgImg = entity.getField(MultipleFields.ORGANIZATION_IMAGE_1);
                orgImgInfo = entity.getField(MultipleFields.ORGANIZATION_IMAGE_2);
                classInfo = entity.getField(MultipleFields.CLASSINFO);
                className = entity.getField(MultipleFields.CLASSNAME);
                phone = entity.getField(MultipleFields.PHONE);
                address = entity.getField(MultipleFields.ADDRESS);

                Glide.with(mContext).load(orgImg).apply(RECYCLER_OPTIONS).into((AppCompatImageView) holder.getView(R.id.item_iv_1));
                Glide.with(mContext).load(orgImgInfo).apply(RECYCLER_OPTIONS).into((AppCompatImageView) holder.getView(R.id.item_iv_2));
                holder.setText(R.id.item_classinfo, className + "|" + classInfo);
                holder.setText(R.id.item_address, address);
                holder.setText(R.id.item_phone, phone);
                break;
            case ItemType.CHOICENESS:

                List<Integer> integerList = entity.getField(MultipleFields.IDS);
                List<String> titleList = entity.getField(MultipleFields.TITLES);
                List<String> textList = entity.getField(MultipleFields.TEXTS);
                List<String> imgUrlList = entity.getField(MultipleFields.URLS);
                int length = 3;
                if (imgUrlList.size() >= length) {

                    Glide.with(mContext).load(imgUrlList.get(0)).apply(RECYCLER_OPTIONS).into((CircleImageView) holder.getView(R.id.civ_subject));
                    Glide.with(mContext).load(imgUrlList.get(1)).apply(RECYCLER_OPTIONS).into((CircleImageView) holder.getView(R.id.civ_teacher));
                    Glide.with(mContext).load(imgUrlList.get(2)).apply(RECYCLER_OPTIONS).into((CircleImageView) holder.getView(R.id.civ_class));

                    holder.setText(R.id.tv_sub_red, textList.get(0));
                    holder.setText(R.id.tv_sub_gary, titleList.get(0));

                    holder.setText(R.id.tv_teacher_red, textList.get(1));
                    holder.setText(R.id.tv_teacher_gray, titleList.get(1));

                    holder.setText(R.id.tv_class_red, textList.get(2));
                    holder.setText(R.id.tv_class_gray, titleList.get(2));
                }


                RelativeLayout rlSubject = holder.getView(R.id.rl_subject);
                rlSubject.setTag(integerList.get(0));
                RelativeLayout rlTeacher = holder.getView(R.id.rl_teacher);
                rlTeacher.setTag(integerList.get(0));
                RelativeLayout rlClass = holder.getView(R.id.rl_class);
                rlTeacher.setTag(rlClass);

                rlClass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (choicenessClickListener !=null){
                            choicenessClickListener.click(0,v);
                        }
                    }
                });

                rlTeacher.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (choicenessClickListener !=null){
                            choicenessClickListener.click(1,v);
                        }
                    }
                });

                rlSubject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (choicenessClickListener !=null){
                            choicenessClickListener.click(2,v);
                        }
                    }
                });
                break;
            case ItemType.CLASS_ITEM:
                String urlImg = entity.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext).load(urlImg).apply(RECYCLER_OPTIONS).into((ImageView) holder.getView(R.id.class_icon));

                String name = entity.getField(MultipleFields.CLASSNAME);
                String courseIntro = entity.getField(MultipleFields.CLASSINFO);
                String site = entity.getField(MultipleFields.ADDRESS);
                String ageGroup = entity.getField(MultipleFields.AGE);
                double distance = entity.getField(MultipleFields.DISTANCE);

                holder.setText(R.id.class_name,name);
                holder.setText(R.id.class_des,courseIntro);
                holder.setText(R.id.class_address, site);
                holder.setText(R.id.class_age,ageGroup);
                holder.setText(R.id.class_distance,distance+"m");

                break;
            default:
                break;
        }
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }

    @Override
    public void onItemClick(int position) {

    }
}
