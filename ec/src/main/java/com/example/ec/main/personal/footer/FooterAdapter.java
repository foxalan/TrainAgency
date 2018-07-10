package com.example.ec.main.personal.footer;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.core.util.date.DateUtil;
import com.example.ec.R;
import com.example.ec.main.personal.PersonalType;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItemEntity;
import com.example.ui.recycler.MultipleRecyclerAdapter;
import com.example.ui.recycler.MultipleViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author alan
 *         Date  2018/7/9.
 *         Function :
 *         Issue :
 */

public class FooterAdapter extends MultipleRecyclerAdapter {

    private IFooterDateListener footerDateListener;

    protected FooterAdapter(List<MultipleItemEntity> data) {
        super(data);

        addItemType(PersonalType.FOOTER_DATE, R.layout.item_personal_footer);
        addItemType(PersonalType.FOOTER_ITEM, R.layout.item_personal_footer_grid);
    }

    public void setFooterDateListener(IFooterDateListener footerDateListener) {
        this.footerDateListener = footerDateListener;
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        switch (entity.getItemType()) {
            case PersonalType.FOOTER_DATE:

                boolean isDelete = entity.getField(MultipleFields.PERSONAL_FOOTER_IS_DELETE);
                boolean isSelect = entity.getField(MultipleFields.PERSONAL_FOOTER_SELECT_DELETE);

                AppCompatCheckBox mCheckFooter = holder.getView(R.id.cb_footer);

                mCheckFooter.setVisibility(isDelete ? View.VISIBLE : View.GONE);
                mCheckFooter.setChecked(isSelect);

                String dateTime = entity.getField(MultipleFields.PERSONAL_FOOTER_DATE);
                Date date = getDateByTime(dateTime);

                AppCompatTextView textView = holder.getView(R.id.tv_footer_date);

                if (DateUtil.isToday(date)) {
                    textView.setText("今天");
                } else if (DateUtil.isYesterDay(date)) {
                    textView.setText("昨天");
                } else {
                    textView.setText((date.getMonth() + 1) + "月" + date.getDate() + "日");
                }

                mCheckFooter.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    entity.setField(MultipleFields.PERSONAL_FOOTER_SELECT_DELETE,isChecked);
                    if (footerDateListener != null) {
                        footerDateListener.onDateClick(isChecked, dateTime);
                    }
                });

                break;
            case PersonalType.FOOTER_ITEM:

                boolean isDeleteItem = entity.getField(MultipleFields.PERSONAL_FOOTER_IS_DELETE_ITEM);
                boolean isSelectItem = entity.getField(MultipleFields.PERSONAL_FOOTER_SELECT_DELETE_ITEM);

                AppCompatCheckBox mCheckFooterItem = holder.getView(R.id.cb_grid_footer_item);

                mCheckFooterItem.setVisibility(isDeleteItem ? View.VISIBLE : View.GONE);
                mCheckFooterItem.setChecked(isSelectItem);
                Log.e("footerdelete","isSelect"+isSelectItem);

                ImageView imageView = holder.getView(R.id.iv_grid_footer_item);
                String img = entity.getField(MultipleFields.PERSONAL_FOOTER_IMG);

                mCheckFooterItem.setOnCheckedChangeListener((buttonView, isChecked) ->
                        entity.setField(MultipleFields.PERSONAL_FOOTER_SELECT_DELETE_ITEM,isChecked));

                Glide.with(mContext).load(img).apply(RECYCLER_OPTIONS).into(imageView);

                break;
            default:
                break;
        }
    }


    private Date getDateByTime(String time){

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }
}
