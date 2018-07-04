package com.example.ec.main.dynamic.message.list;

import android.support.v7.widget.AppCompatTextView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.core.app.Latte;
import com.example.ec.R;
import com.example.ec.main.personal.list.ListItemType;

import java.util.List;

import q.rorbin.badgeview.QBadgeView;

/**
 * @author alan
 *         Date  2018/6/13.
 *         Function :
 *         Issue :
 */

public class MessageAdapter extends BaseMultiItemQuickAdapter<MessageBean, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MessageAdapter(List<MessageBean> data) {
        super(data);

        addItemType(ListItemType.ITEM_IMAGE_TEXT_AVATAR, R.layout.arrow_item_image_text_avatar);

    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {

        ImageView imageView = helper.getView(R.id.tv_arrow_image);
        AppCompatTextView appCompatTextView = helper.getView(R.id.tv_arrow_value);
        imageView.setImageResource(item.getImageUrl());
        appCompatTextView.setText(item.getText());
        QBadgeView mQBadgeView;
        switch (helper.getItemViewType()) {
            case ListItemType.ITEM_IMAGE_TEXT_AVATAR:
                switch (item.getValue()) {
                    case UNREAD:
                        //设置消息
                        mQBadgeView = new QBadgeView(Latte.getApplicationContext());
                        mQBadgeView.bindTarget(imageView).setBadgeNumber(12).setBadgeTextSize(8,true);
                        break;
                    case READ:

                        break;
                    default:

                }

                break;
            default:
                break;
        }
    }
}
