package com.example.ec.main.home.organization;


import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.ec.main.home.subject.SubjectType;
import com.example.ui.recycler.DataConverter;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alan
 *         Date  2018/7/2.
 *         Function :
 *         Issue :
 */

public class OrganizationDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        JSONObject json = JSON.parseObject(getJsonData());
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("adv");

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < dataArray.size(); i++) {
            JSONObject jsonObject = dataArray.getJSONObject(i);
            int id = jsonObject.getInteger("ID");
            String imgUrl = jsonObject.getString("PriceUrl");
            stringList.add(imgUrl);
        }

        final MultipleItemEntity data = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, OrganizationType.ORGANIZATION_TYPE_BANNER)
                .setField(MultipleFields.BANNERS,stringList)
                .build();

        ENTITIES.add(data);

        String className = json.getString("className");
        int browsecount = json.getInteger("browsecount");
        int likecount = json.getInteger("likecount");
        double distance = json.getDouble("distance");

        MultipleItemEntity classEntity = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, OrganizationType.ORGANIZATION_TYPE_CLASS)
                .setField(MultipleFields.HOME_ORGANIZATION_CLASS_NAME, className)
                .setField(MultipleFields.HOME_ORGANIZATION_CLASS_LIKE, likecount)
                .setField(MultipleFields.HOME_ORGANIZATION_CLASS_BROWSE, browsecount)
                .setField(MultipleFields.HOME_ORGANIZATION_CLASS_DISTANCE, distance)
                .build();

        ENTITIES.add(classEntity);

        String address = json.getString("site");
        double longitude = json.getDouble("longitude");
        double latitude = json.getDouble("latitude");
        MultipleItemEntity addressEntity = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, OrganizationType.ORGANIZATION_TYPE_ADDRESS)
                .setField(MultipleFields.HOME_ORGANIZATION_CLASS_ADDRESS, address)
                .setField(MultipleFields.HOME_ORGANIZATION_CLASS_LONGITUDE, longitude)
                .setField(MultipleFields.HOME_ORGANIZATION_CLASS_LATITUDE, latitude)
                .build();
        ENTITIES.add(addressEntity);


        String classInfo = json.getString("classInfo");
        MultipleItemEntity classInfoEntity = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, OrganizationType.ORGANIZATION_TYPE_CLASS_INFO)
                .setField(MultipleFields.HOME_ORGANIZATION_CLASS_INFO, classInfo)
                .build();
        ENTITIES.add(classInfoEntity);


        String courseTime = json.getString("courseTime");
        MultipleItemEntity courseEntity = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, OrganizationType.ORGANIZATION_TYPE_CLASS_COURSE)
                .setField(MultipleFields.HOME_ORGANIZATION_CLASS_COURSE, courseTime)
                .build();
        ENTITIES.add(courseEntity);

        String classTime = json.getString("courseTime");
        MultipleItemEntity classTimeEntity = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, OrganizationType.ORGANIZATION_TYPE_CLASS_TIME)
                .setField(MultipleFields.HOME_ORGANIZATION_CLASS_TIME, classTime)
                .build();
        ENTITIES.add(classTimeEntity);


        String discounts = json.getString("discounts");
        MultipleItemEntity discountsEntity = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, OrganizationType.ORGANIZATION_TYPE_CLASS_DISCOUNT)
                .setField(MultipleFields.HOME_ORGANIZATION_CLASS_DISCOUNTS, discounts)
                .build();
        ENTITIES.add(discountsEntity);


        JSONObject teacher = JSON.parseObject(getJsonData()).getJSONObject("teacher");
        int tId = teacher.getInteger("teacherid");
        Log.e("organization","tILD"+tId + teacher.toJSONString());

        String img = teacher.getString("teacherImg");
        String info = teacher.getString("teacherinfo");
        MultipleItemEntity teacherEntity = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, OrganizationType.ORGANIZATION_TYPE_CLASS_TEACHER)
                .setField(MultipleFields.HOME_ORGANIZATION_TEACHER_ID, tId)
                .setField(MultipleFields.HOME_ORGANIZATION_TEACHER_IMG, img)
                .setField(MultipleFields.HOME_ORGANIZATION_TEACHER_INFO, info)
                .build();
        ENTITIES.add(teacherEntity);


        final JSONArray imgArray = JSON.parseObject(getJsonData()).getJSONArray("imglist");
        List<String> imgList = new ArrayList<>();
        for (int i = 0; i < imgArray.size(); i++) {
            JSONObject image = imgArray.getJSONObject(i);
            int photoId = image.getInteger("photoID");
            String priceUrl = image.getString("PriceUrl");
            imgList.add(priceUrl);
        }

        MultipleItemEntity photoListEntity = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, OrganizationType.ORGANIZATION_TYPE_CLASS_IMAGE_LIST)
                .setField(MultipleFields.HOME_ORGANIZATION_IMGLIST, imgList)
                .build();
        ENTITIES.add(photoListEntity);


        String mobile = json.getString("mobile");


        return ENTITIES;
    }
}
