package com.example.ec.main.personal.footer;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.core.adapter.CustomAdapter;
import com.example.core.adapter.CustomViewHolder;
import com.example.ec.R;

import java.util.List;

import static com.example.ui.recycler.MultipleRecyclerAdapter.RECYCLER_OPTIONS;

/**
 * @author alan
 *         Date  2018/7/9.
 *         Function :
 *         Issue :
 */

public class FooterGridAdapter extends CustomAdapter<FooterBean> {

    public FooterGridAdapter(Context mContext, List<FooterBean> mData, int resID) {
        super(mContext, mData, resID);
    }

    @Override
    public void convert(CustomViewHolder viewHolder, int position) {

        ImageView imageView =  viewHolder.getView(R.id.iv_grid_footer_item);
        Glide.with(mContext).load(mData.get(position).getImg()).apply(RECYCLER_OPTIONS).into(imageView);
        Log.e("footer",mData.get(position).getImg());
    }
}
