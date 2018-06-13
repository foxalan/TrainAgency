package com.example.ec.main.home.map.bean;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.example.ec.R;
import com.example.ui.adapter.CustomAdapter;
import com.example.ui.adapter.CustomViewHolder;

import java.util.List;

/**
 * @author alan
 *         Date  2018/6/13.
 *         Function : 搜索位置结果的ListView
 *         Issue :
 */

public class MapSearchAdapter extends CustomAdapter<PoiInfo> {

    public MapSearchAdapter(Context mContext, List<PoiInfo> mData, int resID) {
        super(mContext, mData, resID);
    }

    @Override
    public void convert(CustomViewHolder viewHolder, int position) {
        AppCompatTextView textView = viewHolder.getView(R.id.tv_item_map_location);
        textView.setText(mData.get(position).address);
    }
}
