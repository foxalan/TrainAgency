package com.example.ec.detail;


import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.ec.R;
import com.example.ui.recycler.ItemType;
import com.example.ui.recycler.MultipleItemEntity;
import com.example.ui.recycler.MultipleRecyclerAdapter;
import com.example.ui.recycler.MultipleViewHolder;

import java.util.List;


/**
 * @author alan
 *
 */

public class RecyclerImageAdapter extends MultipleRecyclerAdapter {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .dontAnimate();

    protected RecyclerImageAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.SINGLE_BIG_IMAGE, R.layout.item_image);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        final int type = holder.getItemViewType();
        switch (type) {
            case ItemType.SINGLE_BIG_IMAGE:
//                final AppCompatImageView imageView = holder.getView(R.id.image_rv_item);
//                final String url = entity.getField(MultipleFields.IMAGE_URL);
//                Glide.with(mContext)
//                        .load(url)
//                        .into(imageView);
                break;
            default:
                break;
        }
    }
}
