package com.example.ec.main.personal.notice.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.core.app.AccountManager;
import com.example.core.delegate.LatteDelegate;
import com.example.core.net.RestClient;
import com.example.ec.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alan
 *         Date  2018/7/6.
 *         Function :
 *         Issue :
 */

public class NoticeTeacherDelegate extends LatteDelegate {

    private SwipeMenuListView listView;
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

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }

    private void initData() {
        RestClient.builder()
                .url("ClassLikeList")
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
}
