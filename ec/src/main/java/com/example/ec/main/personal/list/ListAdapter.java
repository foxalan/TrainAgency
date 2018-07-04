package com.example.ec.main.personal.list;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ec.R;


import java.util.List;

/**
 * @author alan
 */

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop();

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListItemType.ITEM_IMAGE_TEXT_AVATAR, R.layout.arrow_item_image_text_avatar);
        addItemType(ListItemType.ITEM_USER_AVATAR, R.layout.arrow_item_user_avatar);
        addItemType(ListItemType.ITEM_BLANK, R.layout.arrow_item_blank_layout);
        addItemType(ListItemType.ITEM_TEXT_TEXT, R.layout.arrow_item_text_text);
        addItemType(ListItemType.ITEM_TEXT_AVATAR, R.layout.arrow_item_text_avatar);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        switch (helper.getItemViewType()) {
            case ListItemType.ITEM_IMAGE_TEXT_AVATAR:
                helper.setImageResource(R.id.tv_arrow_image, item.getImageUrl());
                helper.setText(R.id.tv_arrow_value, item.getValue());
                break;
            case ListItemType.ITEM_USER_AVATAR:
                Glide.with(mContext)
                        .load(item.getImageUrl())
                        .apply(OPTIONS)
                        .into((ImageView) helper.getView(R.id.img_arrow_avatar));
                break;
            case ListItemType.ITEM_BLANK:
                break;
            case ListItemType.ITEM_TEXT_TEXT:
                helper.setText(R.id.tv_arrow_text, item.getText());
                helper.setText(R.id.tv_arrow_context, item.getValue());
                break;
            case ListItemType.ITEM_TEXT_AVATAR:
                helper.setText(R.id.tv_arrow_text,item.getValue());
                break;
            default:
                break;
        }
    }
}
