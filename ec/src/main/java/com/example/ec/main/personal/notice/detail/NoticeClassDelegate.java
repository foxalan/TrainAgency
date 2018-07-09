package com.example.ec.main.personal.notice.detail;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ListView;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.core.app.AccountManager;
import com.example.core.delegate.LatteDelegate;
import com.example.core.net.RestClient;

import com.example.ec.R;

import java.util.ArrayList;
import java.util.List;




/**
 * @author alan
 *         Date  2018/7/4.
 *         Function : 关注列表界面
 *         Issue :
 */

public class NoticeClassDelegate extends LatteDelegate {

    private ListView listView;
    private NoticeAdapter adapter;
    private List<NoticeBean> noticeBeanList = new ArrayList<>();


    @Override
    public Object setLayout() {
        return R.layout.delegate_listview;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        listView = rootView.findViewById(R.id.lv_notice);
        initData();
    }



    /**
     * http://192.168.1.186/Select/TeacherLikeList?userid=1
     */
    private void initData(){

        RestClient.builder()
                .url("TeacherLikeList")
                .params("userid",getUserId())
                .loader(getContext())
                .success(response -> {
                    final JSONArray array = JSON.parseArray(response);
                    for (int i = 0 ; i<array.size();i++){
                        JSONObject object = array.getJSONObject(i);
                        int  id = object.getInteger("targerid");
                        String name = object.getString("name");
                        String img = object.getString("img");

                        NoticeBean bean = new NoticeBean(id,img,name,"18202710074",true);
                        noticeBeanList.add(bean);
                    }

                    adapter = new NoticeAdapter(getContext(),noticeBeanList,R.layout.item_personal_notice);
                    listView.setAdapter(adapter);
                })
                .build()
                .get();
    }

    private String getUserId(){
       return AccountManager.getUserId();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);


    }
}
