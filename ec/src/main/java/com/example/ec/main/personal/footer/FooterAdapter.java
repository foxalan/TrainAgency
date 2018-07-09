package com.example.ec.main.personal.footer;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ec.R;
import com.example.ec.main.personal.PersonalType;
import com.example.ec.main.personal.list.ListBean;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItemEntity;
import com.example.ui.recycler.MultipleRecyclerAdapter;
import com.example.ui.recycler.MultipleViewHolder;

import java.util.ArrayList;
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
                String date = entity.getField(MultipleFields.PERSONAL_FOOTER_DATE);
                AppCompatTextView textView = holder.getView(R.id.tv_footer_date);
                textView.setText(date);
                break;
            case PersonalType.FOOTER_ITEM:
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
