package com.example.ec.main.home.subject.list;

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
 *         Date  2018/6/30.
 *         Function :
 *         Issue :
 */

public class SubjectNameDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");

        Log.e("subjectname",getJsonData());

//            "id": 4,
//                    "className": "语文培训班4",
//                    "courseIntro": "语文和修养合二唯一",
//                    "site": "武汉音乐学院",
//                    "ageGroup": "适合8岁-15岁学习",
//                    "classImg": "http://192.168.1.186/img/ClassImg/class-1105.png",
//                    "locationX": "114.302234",
//                    "locationY": "30.543991",
//                    "regionsID": 1900,
//                    "distance": 12329670.0,
//                    "regions": {
//                "name": "武昌区 ",
//                        "cityname": "武汉市 ",
//                        "provincename": "湖北省 "
//

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
