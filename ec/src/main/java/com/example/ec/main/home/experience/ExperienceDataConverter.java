package com.example.ec.main.home.experience;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.ec.main.home.subject.SubjectType;
import com.example.ui.recycler.DataConverter;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * @author alan
 *         Date  2018/7/5.
 *         Function :
 *         Issue :
 */

public class ExperienceDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");

        Log.e("subjectname",getJsonData());
        for(int i =0;i<dataArray.size();i++){

            JSONObject jsonObject = dataArray.getJSONObject(i);
            int id = jsonObject.getInteger("id");
            String className = jsonObject.getString("className");
            String courseIntro = jsonObject.getString("courseIntro");
            String site = jsonObject.getString("site");
            String ageGroup = jsonObject.getString("ageGroup");
            String imgUrl = jsonObject.getString("classImg");

            double distance = jsonObject.getDouble("distance");

            final MultipleItemEntity data = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, SubjectType.SUBJECT_ITEM)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.CLASSNAME,className)
                    .setField(MultipleFields.IMAGE_URL,imgUrl)
                    .setField(MultipleFields.CLASSINFO,courseIntro)
                    .setField(MultipleFields.ADDRESS,site)
                    .setField(MultipleFields.AGE,ageGroup)
                    .setField(MultipleFields.DISTANCE,distance)
                    .build();

            ENTITIES.add(data);

        }

        return ENTITIES;
    }
}
