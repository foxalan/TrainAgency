package com.example.ec.main.home.teacher;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.core.delegate.LatteDelegate;
import com.example.ec.main.discover.DisCoverItemType;
import com.example.ui.recycler.DataConverter;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alan
 *         Date  2018/7/5.
 *         Function :
 *         Issue :
 */

public class TeacherDataconveter extends DataConverter {

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
                .setField(MultipleFields.ITEM_TYPE, DisCoverItemType.DISCOVER_TEACHER_TYPE_BANNER)
                .setField(MultipleFields.DISCOVER_TEACHER_BANNERS,stringList)
                .build();

        ENTITIES.add(data);

        final JSONArray teacherArray = JSON.parseObject(getJsonData()).getJSONArray("teacherInfo");

        for (int i = 0; i < teacherArray.size(); i++) {
            JSONObject jsonObject = teacherArray.getJSONObject(i);
            int id = jsonObject.getInteger("ID");
            String imgUrl = jsonObject.getString("teacherImg");
            String name = jsonObject.getString("name");
            String resume = jsonObject.getString("resume");
            String className = jsonObject.getString("className");
            String mobile = jsonObject.getString("mobile");

            final MultipleItemEntity teacher = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, DisCoverItemType.DISCOVER_TEACHER_TYPE_INFO)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.DISCOVER_TEACHER_IMG,imgUrl)
                    .setField(MultipleFields.DISCOVER_TEACHER_NAME,name)
                    .setField(MultipleFields.DISVOCER_TEACHER_INFO,resume)
                    .setField(MultipleFields.DISVOCER_TEACHER_CLASS,className)
                    .setField(MultipleFields.DISVOCER_TEACHER_MOBILE,mobile)
                    .setField(MultipleFields.BANNERS,stringList)
                    .build();
            ENTITIES.add(teacher);
        }

        return ENTITIES;
    }
}
