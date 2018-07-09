package com.example.ec.main.personal.footer;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
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

    protected FooterAdapter(List<MultipleItemEntity> data) {
        super(data);

        addItemType(PersonalType.FOOTER_DATE, R.layout.item_personal_footer);
        addItemType(PersonalType.FOOTER_ITEM, R.layout.item_personal_footer_grid);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        switch (entity.getItemType()) {
            case PersonalType.FOOTER_DATE:

                boolean isDelete = entity.getField(MultipleFields.PERSONAL_FOOTER_IS_DELETE);
                AppCompatCheckBox mCheckFooter = holder.getView(R.id.cb_footer);
                mCheckFooter.setVisibility(isDelete? View.VISIBLE:View.GONE);

                if (!mCheckFooter.isChecked()){
                    String dateTime = entity.getField(MultipleFields.PERSONAL_FOOTER_DATE);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    Date date = null;
                    try {
                        date = format.parse(dateTime);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Log.e("datealan","date"+"==="+dateTime+date.toString());
                    AppCompatTextView textView = holder.getView(R.id.tv_footer_date);
                    if (DateUtil.isToday(date)){
                        textView.setText("今天");
                    }else if (DateUtil.isYesterDay(date)){
                        textView.setText("昨天");
                    }else {
                        textView.setText((date.getMonth()+1)+"月"+date.getDate()+"日");
                    }
                }
                break;
            case PersonalType.FOOTER_ITEM:

                boolean isDeleteItem= entity.getField(MultipleFields.PERSONAL_FOOTER_IS_DELETE_ITEM);
                AppCompatCheckBox mCheckFooterItem = holder.getView(R.id.cb_grid_footer_item);
                mCheckFooterItem.setVisibility(isDeleteItem? View.VISIBLE:View.GONE);

                ImageView imageView =  holder.getView(R.id.iv_grid_footer_item);
                String img = entity.getField(MultipleFields.PERSONAL_FOOTER_IMG);
                Log.e("footerimg",img);
                Glide.with(mContext).load(img).apply(RECYCLER_OPTIONS).into(imageView);
                break;
            default:
                break;
        }
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }
}
