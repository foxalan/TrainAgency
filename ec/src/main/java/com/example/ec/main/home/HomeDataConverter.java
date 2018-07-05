package com.example.ec.main.home;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.ec.main.home.subject.SubjectType;
import com.example.ui.recycler.DataConverter;
import com.example.ui.recycler.ItemType;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItemEntity;


import java.util.ArrayList;
import java.util.List;

/**
 * @author alan
 *         JSON
 */

public final class HomeDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        Log.e("foxconn", getJsonData().toString());

        final JSONArray advArray = JSON.parseObject(getJsonData()).getJSONArray("adv");

        final int size = advArray.size();
        final ArrayList<String> bannerImages = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = advArray.getJSONObject(i);
            String banner = jsonObject.getString("PriceUrl");
            Log.e("Foxconn", banner);
            bannerImages.add(banner);
        }

        final MultipleItemEntity entity = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, ItemType.BANNER)
                .setField(MultipleFields.SPAN_SIZE, 5)
                .setField(MultipleFields.ID, 0)
                .setField(MultipleFields.BANNERS, bannerImages)
                .build();

        ENTITIES.add(entity);

        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        for (int i = 0; i < dataArray.size(); i++) {
            JSONObject jsonObject = dataArray.getJSONObject(i);
            int id = jsonObject.getInteger("ID");
            String text = jsonObject.getString("subjectTypeName");
            String url = jsonObject.getString("subjectTypeimgurl");

            final MultipleItemEntity data = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.TEXT_IMAGE)
                    .setField(MultipleFields.SPAN_SIZE, 1)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, text)
                    .setField(MultipleFields.IMAGE_URL, url)
                    .build();

            ENTITIES.add(data);

        }

        final JSONArray recommendArray = JSON.parseObject(getJsonData()).getJSONArray("Recommend");
        Log.e("foxconn", recommendArray.toJSONString() + "===");
        for (int i = 0; i < recommendArray.size(); i++) {
            JSONObject jsonObject = recommendArray.getJSONObject(i);
            String id = jsonObject.getString("ID");
            String organization_image_1 = jsonObject.getString("RecommendImg");
            String organization_image_2 = jsonObject.getString("classImg");
            String className = jsonObject.getString("className");
            String classInfo = jsonObject.getString("classInfo");
            String address = jsonObject.getString("site");
            String phone = jsonObject.getString("mobile");

            final MultipleItemEntity data = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.RECOMMEND)
                    .setField(MultipleFields.ID,1)
                    .setField(MultipleFields.SPAN_SIZE, 5)
                    .setField(MultipleFields.ORGANIZATION_IMAGE_1, organization_image_1)
                    .setField(MultipleFields.ORGANIZATION_IMAGE_2, organization_image_2)
                    .setField(MultipleFields.CLASSNAME, className)
                    .setField(MultipleFields.CLASSINFO, classInfo)
                    .setField(MultipleFields.ADDRESS, address)
                    .setField(MultipleFields.PHONE, phone)
                    .build();
            ENTITIES.add(data);
        }

        final JSONArray choicenessArray = JSON.parseObject(getJsonData()).getJSONArray("choiceness");
        Log.e("foxconn", recommendArray.toJSONString() + "===");


        List<Integer> integerList = new ArrayList<>();
        List<String> picList = new ArrayList<>();
        List<String> textList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();

        for (int i = 0; i < choicenessArray.size(); i++) {
            JSONObject jsonObject = choicenessArray.getJSONObject(i);
            int id = jsonObject.getInteger("photoID");
            String picUrl = jsonObject.getString("PriceUrl");
            String text = jsonObject.getString("text");
            String title = jsonObject.getString("title");

            integerList.add(id);
            picList.add(picUrl);
            textList.add(text);
            titleList.add(title);
        }

        final MultipleItemEntity data = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, ItemType.CHOICENESS)
                .setField(MultipleFields.SPAN_SIZE, 5)
                .setField(MultipleFields.IDS, integerList)
                .setField(MultipleFields.TEXTS, textList)
                .setField(MultipleFields.TITLES,titleList)
                .setField(MultipleFields.URLS, picList)
                .build();

        ENTITIES.add(data);

        final MultipleItemEntity classHeader = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, ItemType.TEXT_HEADER)
                .setField(MultipleFields.SPAN_SIZE, 5)
                .build();

        ENTITIES.add(classHeader);

        final JSONArray classArray = JSON.parseObject(getJsonData()).getJSONArray("classlist");
        for(int i =0;i<classArray.size();i++){

            JSONObject jsonObject = classArray.getJSONObject(i);
            int id = jsonObject.getInteger("id");
            String className = jsonObject.getString("className");
            String courseIntro = jsonObject.getString("courseIntro");
            String site = jsonObject.getString("site");
            String ageGroup = jsonObject.getString("ageGroup");
            String imgUrl = jsonObject.getString("classImg");
            double distance = jsonObject.getDouble("distance");

            final MultipleItemEntity classItem = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.CLASS_ITEM)
                    .setField(MultipleFields.SPAN_SIZE,5)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.CLASSNAME,className)
                    .setField(MultipleFields.IMAGE_URL,imgUrl)
                    .setField(MultipleFields.CLASSINFO,courseIntro)
                    .setField(MultipleFields.ADDRESS,site)
                    .setField(MultipleFields.AGE,ageGroup)
                    .setField(MultipleFields.DISTANCE,distance)
                    .build();

            ENTITIES.add(classItem);

        }


//        for (int i = 0; i < size; i++) {
//            final JSONObject data = dataArray.getJSONObject(i);
//
//            final String imageUrl = data.getString("imageUrl");
//            final String text = data.getString("text");
//            final int spanSize = data.getInteger("spanSize");
//            final int id = data.getInteger("goodsId");
//            final JSONArray banners = data.getJSONArray("banners");
//
//            final ArrayList<String> bannerImages = new ArrayList<>();
//            int type = 0;
//            if (imageUrl == null && text != null) {
//                type = ItemType.TEXT;
//            } else if (imageUrl != null && text == null) {
//                type = ItemType.IMAGE;
//            } else if (imageUrl != null) {
//                type = ItemType.TEXT_IMAGE;
//            } else if (banners != null) {
//                type = ItemType.BANNER;
//                //Banner的初始化
//                final int bannerSize = banners.size();
//                for (int j = 0; j < bannerSize; j++) {
//                    final String banner = banners.getString(j);
//                    bannerImages.add(banner);
//                }
//            }
//
//            final MultipleItemEntity entity = MultipleItemEntity.builder()
//                    .setField(MultipleFields.ITEM_TYPE,type)
//                    .setField(MultipleFields.SPAN_SIZE,spanSize)
//                    .setField(MultipleFields.ID,id)
//                    .setField(MultipleFields.TEXT,text)
//                    .setField(MultipleFields.IMAGE_URL,imageUrl)
//                    .setField(MultipleFields.BANNERS,bannerImages)
//                    .build();
//
//            ENTITIES.add(entity);
//
//        }

        return ENTITIES;
    }
}
