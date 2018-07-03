package com.example.ec.main.discover.message;

import com.example.ec.R;
import com.example.ec.main.discover.DisCoverItemType;
import com.example.ui.recycler.MultipleItemEntity;
import com.example.ui.recycler.MultipleRecyclerAdapter;
import com.example.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * @author alan
 *         Date  2018/7/3.
 *         Function :
 *         Issue :
 */

public class MessageAdapter extends MultipleRecyclerAdapter {

    protected MessageAdapter(List<MultipleItemEntity> data) {
        super(data);

        addItemType(DisCoverItemType.DISCOVER_MESSAGE_TYPE_BANNER, R.layout.item_banner);
        addItemType(DisCoverItemType.DISCOVER_TEACHER_TYPE_INFO, R.layout.item_discover_message);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);

        switch (entity.getItemType()) {
            case DisCoverItemType.DISCOVER_MESSAGE_TYPE_BANNER:

                break;
            case DisCoverItemType.DISCOVER_MESSAGE_TYPE_INFO:
                holder.getView(R.id.tv_msg_browse);
                holder.getView(R.id.tv_msg_title);
                holder.getView(R.id.tv_msg_browse);
                holder.getView(R.id.tv_msg_date);
                break;
            default:
                break;
        }

    }
}
