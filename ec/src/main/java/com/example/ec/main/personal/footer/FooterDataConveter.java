package com.example.ec.main.personal.footer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.ec.main.personal.PersonalType;
import com.example.ui.recycler.DataConverter;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * @author alan
 *         Date  2018/7/9.
 *         Function :
 *         Issue :
 */

public class FooterDataConveter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        JSONArray array = JSON.parseArray(getJsonData());
        for (int i = 0 ;i<array.size();i++){
            JSONArray jsonArray = array.getJSONArray(i);

            final MultipleItemEntity data = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, PersonalType.FOOTER_DATE)
                    .setField(MultipleFields.PERSONAL_FOOTER_DATE,jsonArray.getJSONObject(0).getString("time"))
                    .setField(MultipleFields.PERSONAL_FOOTER_IS_DELETE,false)
                    .setField(MultipleFields.PERSONAL_FOOTER_SELECT_DELETE,false)
                    .setField(MultipleFields.SPAN_SIZE,3)
                    .build();

            ENTITIES.add(data);

            for (int j =0;j<jsonArray.size();j++){
                JSONObject jsonObject = jsonArray.getJSONObject(j);
                int id = jsonObject.getInteger("ID");
                int  heatTarget = jsonObject.getInteger("heatTarget");
                String img = jsonObject.getString("img");
                int targetId = jsonObject.getInteger("targerid");
                String time = jsonObject.getString("time");

                final MultipleItemEntity item = MultipleItemEntity.builder()
                        .setField(MultipleFields.ITEM_TYPE, PersonalType.FOOTER_ITEM)
                        .setField(MultipleFields.PERSONAL_FOOTER_DATE,time)
                        .setField(MultipleFields.PERSONAL_FOOTER_ID,id)
                        .setField(MultipleFields.PERSONAL_FOOTER_SELECT_DELETE_ITEM,false)
                        .setField(MultipleFields.PERSONAL_FOOTER_IMG,img)
                        .setField(MultipleFields.PERSONAL_FOOTER_IS_DELETE_ITEM,false)
                        .setField(MultipleFields.SPAN_SIZE,1)
                        .build();
                ENTITIES.add(item);
            }
        }

        return ENTITIES;
    }
}
