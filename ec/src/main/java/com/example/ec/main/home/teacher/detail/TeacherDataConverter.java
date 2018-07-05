package com.example.ec.main.home.teacher.detail;




import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.ec.main.home.organization.OrganizationType;
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

public class TeacherDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        JSONObject json = JSON.parseObject(getJsonData());
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("adv");

        //ADV
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


        //老师
        String teacherName = json.getString("name");
        int browsecount = json.getInteger("browsecount");
        int likecount = json.getInteger("likecount");


        MultipleItemEntity classEntity = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, OrganizationType.ORGANIZATION_TYPE_CLASS)
                .setField(MultipleFields.HOME_ORGANIZATION_CLASS_NAME, teacherName)
                .setField(MultipleFields.HOME_ORGANIZATION_CLASS_LIKE, likecount)
                .setField(MultipleFields.HOME_ORGANIZATION_CLASS_BROWSE, browsecount)
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


        String img = json.getString("teacherImg");
        String info = json.getString("resume");
        MultipleItemEntity teacherEntity = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, OrganizationType.ORGANIZATION_TYPE_CLASS_TEACHER)
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
        int state = json.getInteger("likestate");

        MultipleItemEntity stateEntity = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE,OrganizationType.ORGANIZATION_TYPE_CLASS_MOBILE)
                .setField(MultipleFields.HOME_ORGANIZATION_MOBILE,mobile)
                .setField(MultipleFields.HOME_ORGANIZATION_STATE,state)
                .build();

        ENTITIES.add(stateEntity);
        return ENTITIES;
    }
}
