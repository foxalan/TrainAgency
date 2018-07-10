package com.example.ec.main.personal.notice.detail;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.core.app.AccountManager;
import com.example.core.app.Latte;
import com.example.core.delegate.LatteDelegate;
import com.example.core.net.RestClient;

import com.example.ec.R;
import com.example.ec.main.home.organization.OrganizationDelegate;
import com.example.ec.main.home.teacher.detail.TeacherDetailDelegate;
import com.example.ec.main.personal.notice.NoticeDelegate;

import java.util.ArrayList;
import java.util.List;




/**
 * @author alan
 *         Date  2018/7/4.
 *         Function : 老师关注列表界面
 *         Issue :
 */

@SuppressLint("ValidFragment")
public class NoticeTeacherDelegate extends LatteDelegate {

    private ListView listView;
    private NoticeAdapter adapter;
    private List<NoticeBean> noticeBeanList = new ArrayList<>();
    private NoticeDelegate noticeDelegate;


    public NoticeTeacherDelegate(NoticeDelegate noticeDelegate){
        this.noticeDelegate = noticeDelegate;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_listview;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        listView = rootView.findViewById(R.id.lv_notice);
        adapter = new NoticeAdapter(getContext(),noticeBeanList,R.layout.item_personal_notice);

        initData();

        adapter.setUnNoticeListener(new IUnNoticeListener() {
            @Override
            public void unNotice(int type, int position, View v) {
                if (type == NoticeType.NOTICE_TEACHER_TYPE){
                    Log.e("cancelNotice","classID:"+noticeBeanList.get(position).getId());
                    RestClient.builder()
                            .loader(getContext())
                            .url("http://192.168.1.186/Remove/dellike")
                            .params("userid", getUserId())
                            .params("teacherid", noticeBeanList.get(position).getId())
                            .success(response -> {
                                Log.e("notice","remove:"+response);
                                final JSONObject object = JSON.parseObject(response);
                                String msg = object.getString("msg");
                                Latte.getHandler().post(() -> {
                                    Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                                    noticeBeanList.get(position).setNotice(true);
                                    noticeBeanList.remove(position);
                                    adapter.notifyDataSetChanged();
                                });
                            })
                            .build()
                            .post();
                }
            }

            @Override
            public void toItem(int type, int position, View v) {

                    if (type == NoticeType.NOTICE_TEACHER_TYPE){
                        int classId = noticeBeanList.get(position).getId();
                        Log.e("listview","onclick"+classId);
                        noticeDelegate.getSupportDelegate().start(TeacherDetailDelegate.create(classId));
                    }

            }
        });
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

                        NoticeBean bean = new NoticeBean(NoticeType.NOTICE_TEACHER_TYPE,id,img,name,"18202710074",false);
                        noticeBeanList.add(bean);
                    }

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
