package com.example.ec.main.home;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.ui.recycler.DataConverter;
import com.example.ui.recycler.ItemType;
import com.example.ui.recycler.MultipleFields;
import com.example.ui.recycler.MultipleItemEntity;




import java.util.ArrayList;

/**
 * Created by 傅令杰
 */

public final class HomeDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert()  {
        String value = "{\n" +
                "  \"code\": 0,\n" +
                "  \"message\": \"OK\",\n" +
                "  \"total\": 100,\n" +
                "  \"page_size\": 6,\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"goodsId\": 0,\n" +
                "      \"spanSize\": 4,\n" +
                "      \"banners\": [\n" +
                "        \"https://i8.mifile.cn/v1/a1/251f0880-423e-fa2d-3c18-1d3ec23f9912.webp\",\n" +
                "        \"https://i8.mifile.cn/v1/a1/49dfd019-9504-abb5-34bb-26f425b67e45.webp\",\n" +
                "        \"https://cdn.cnbj0.fds.api.mi-img.com/b2c-mimall-media/b9540da01aef9a00a5c640b1c98b955a.jpg\"\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"goodsId\": 1,\n" +
                "      \"text\": \"明星单品\",\n" +
                "      \"imageUrl\": \"https://i8.mifile.cn/v1/a1/1d338200-1be1-f868-9695-9d5ae0d6c2c6.webp\",\n" +
                "      \"spanSize\": 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"goodsId\": 2,\n" +
                "      \"text\": \"小米5c  64GB 移动版\",\n" +
                "      \"imageUrl\": \"https://i8.mifile.cn/v1/a1/04629084-7810-d1fb-6f4a-a0c52433ca29.webp?width=360&height=360\",\n" +
                "      \"spanSize\": 2\n" +
                "    },\n" +
                "    {\n" +
                "      \"goodsId\": 3,\n" +
                "      \"text\": \"米家智能摄像机\",\n" +
                "      \"imageUrl\": \"https://i8.mifile.cn/v1/a1/375bd3a4-aab9-f77b-f6a1-5dbf01087495.webp?width=360&height=360\",\n" +
                "      \"spanSize\": 2\n" +
                "    },\n" +
                "    {\n" +
                "      \"goodsId\": 4,\n" +
                "      \"imageUrl\": \"https://i8.mifile.cn/v1/a1/656a5863-6af1-6302-4e36-a33bd49e45cb.webp?width=360&height=360\",\n" +
                "      \"spanSize\": 2\n" +
                "    },\n" +
                "    {\n" +
                "      \"goodsId\": 5,\n" +
                "      \"imageUrl\": \"https://cdn.cnbj0.fds.api.mi-img.com/b2c-mimall-media/cbcdb6c054d45c1afc955c87329e96f1.jpg?width=360&height=360\",\n" +
                "      \"spanSize\": 2\n" +
                "    },\n" +
                "    {\n" +
                "      \"goodsId\": 6,\n" +
                "      \"imageUrl\": \"https://i8.mifile.cn/v1/a1/f093e0a8-e3d8-4fc8-deb7-af25ea3d8663.webp?width=360&height=360\",\n" +
                "      \"spanSize\": 2\n" +
                "    },\n" +
                "    {\n" +
                "      \"goodsId\": 7,\n" +
                "      \"imageUrl\": \"https://i8.mifile.cn/v1/a1/b6c55f3b-d4ac-b2be-8d7c-3c818a79030a.webp?width=360&height=360\",\n" +
                "      \"spanSize\": 2\n" +
                "    },\n" +
                "    {\n" +
                "      \"goodsId\": 5,\n" +
                "      \"text\": \"智能生活，从这里开始\",\n" +
                "      \"spanSize\": 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"goodsId\": 5,\n" +
                "      \"text\": \"测试描述5\",\n" +
                "      \"imageUrl\": \"https://i8.mifile.cn/v1/a1/6cc739d8-ae51-779a-3707-2f1d20a558bf.webp?width=720&heihgt=440\",\n" +
                "      \"spanSize\": 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"goodsId\": 6,\n" +
                "      \"text\": \"测试描述6\",\n" +
                "      \"imageUrl\": \"http://imgsrc.baidu.com/baike/pic/item/0b55b319ebc4b745a58395aecffc1e178a821576.jpg\",\n" +
                "      \"spanSize\": 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"goodsId\": 5,\n" +
                "      \"text\": \"我又是野广告\",\n" +
                "      \"spanSize\": 4\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        //final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");

        final JSONArray dataArray = JSON.parseObject(value).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);

            final String imageUrl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int spanSize = data.getInteger("spanSize");
            final int id = data.getInteger("goodsId");
            final JSONArray banners = data.getJSONArray("banners");

            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;
            if (imageUrl == null && text != null) {
                type = ItemType.TEXT;
            } else if (imageUrl != null && text == null) {
                type = ItemType.IMAGE;
            } else if (imageUrl != null) {
                type = ItemType.TEXT_IMAGE;
            } else if (banners != null) {
                type = ItemType.BANNER;
                //Banner的初始化
                final int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            }

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE,type)
                    .setField(MultipleFields.SPAN_SIZE,spanSize)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.TEXT,text)
                    .setField(MultipleFields.IMAGE_URL,imageUrl)
                    .setField(MultipleFields.BANNERS,bannerImages)
                    .build();

            ENTITIES.add(entity);

        }

        return ENTITIES;
    }
}
