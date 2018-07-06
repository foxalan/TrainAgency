package com.example.ec.main.home.subject.good;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.ec.main.discover.DisCoverItemType;
import com.example.ec.main.home.subject.SubjectType;
import com.example.ui.recycler.DataConverter;
import com.example.ui.recycler.ItemType;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alan
 *         Date  2018/7/6.
 *         Function :
 *         Issue :
 */

public class GreatSujectDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("adv");

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < dataArray.size(); i++) {
            JSONObject jsonObject = dataArray.getJSONObject(i);
            int id = jsonObject.getInteger("ID");
            String imgUrl = jsonObject.getString("PriceUrl");
            stringList.add(imgUrl);
        }

        final MultipleItemEntity data = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, ItemType.BANNER)
                .setField(MultipleFields.BANNERS,stringList)
                .build();

        ENTITIES.add(data);
        final JSONArray classListArray = JSON.parseObject(getJsonData()).getJSONArray("classlist");

        Log.e("subjectname",getJsonData());
        for(int i =0;i<classListArray.size();i++){

            JSONObject jsonObject = classListArray.getJSONObject(i);
            int id = jsonObject.getInteger("id");
            String className = jsonObject.getString("className");
            String courseIntro = jsonObject.getString("courseIntro");
            String site = jsonObject.getString("site");
            String ageGroup = jsonObject.getString("ageGroup");
            String imgUrl = jsonObject.getString("classImg");
            double distance = jsonObject.getDouble("distance");

            final MultipleItemEntity  classList= MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, SubjectType.SUBJECT_ITEM)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.CLASSNAME,className)
                    .setField(MultipleFields.IMAGE_URL,imgUrl)
                    .setField(MultipleFields.CLASSINFO,courseIntro)
                    .setField(MultipleFields.ADDRESS,site)
                    .setField(MultipleFields.AGE,ageGroup)
                    .setField(MultipleFields.DISTANCE,distance)
                    .build();

            ENTITIES.add(classList);
        }

        return ENTITIES;
    }
}
