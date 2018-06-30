package com.example.ec.main.home.subject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.ui.recycler.DataConverter;
import com.example.ui.recycler.ItemType;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * @author alan
 *         Date  2018/6/30.
 *         Function :
 *         Issue :
 */

public class SubjectDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");

        for(int i =0;i<dataArray.size();i++){
            JSONObject jsonObject = dataArray.getJSONObject(i);
            int id = jsonObject.getInteger("ID");
            String text = jsonObject.getString("subjectName");
            String url = jsonObject.getString("subjectImg");

            final MultipleItemEntity data = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, SubjectType.SUBJECT_TYPE)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.TEXT,text)
                    .setField(MultipleFields.IMAGE_URL,url)
                    .setField(MultipleFields.SPAN_SIZE,1)
                    .build();

            ENTITIES.add(data);

        }

        return ENTITIES;
    }
}
