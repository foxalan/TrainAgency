package com.example.ec.main.home.message.list;

import android.support.v7.widget.AppCompatTextView;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ec.R;
import com.example.ec.main.personal.list.ListItemType;

import java.util.List;

/**
 * @author alan
 *         Date  2018/6/13.
 *         Function :
 *         Issue :
 */

public class MessageAdapter extends BaseMultiItemQuickAdapter<MessageBean,BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MessageAdapter(List<MessageBean> data) {
        super(data);

        addItemType(ListItemType.ITEM_NORMAL, R.layout.arrow_item_layout);

    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {
        switch (helper.getItemViewType()) {
            case ListItemType.ITEM_NORMAL:
                AppCompatTextView appCompatTextView =  helper.getView(R.id.tv_arrow_value);
                appCompatTextView.setText(item.getText());
                break;
            default:
                break;
        }
    }
}
