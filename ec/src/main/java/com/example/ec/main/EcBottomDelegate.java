package com.example.ec.main;

import android.util.Log;

import com.example.core.delegate.bottom.BaseBottomDelegate;
import com.example.core.delegate.bottom.BottomItemDelegate;
import com.example.core.delegate.bottom.BottomTabBean;
import com.example.core.delegate.bottom.ItemBuilder;
import com.example.core.net.RestClient;
import com.example.ec.main.discover.DiscoverDelegate;
import com.example.ec.main.dynamic.DynamicDelegate;
import com.example.ec.main.home.HomeDelegate;
import com.example.ec.main.personal.PersonalDelegate;

import java.util.LinkedHashMap;

/**
 * @author alan
 *         Date  2018/6/5.
 *         Function :
 *         Issue :
 */

public class EcBottomDelegate extends BaseBottomDelegate {



    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {

        //http://10.0.0.2/api/
        //account=18202710074&password=123456&name=alan
        RestClient.builder()
                .url("http://10.0.0.2/api/account/register")
                .params("account","18202710074")
                .params("password","123456")
                .params("name","alan")
                .params("pushId","369")
                .success(response -> Log.e("latteCat","success"+response))
                .failure(() -> Log.e("latteCat","failure"))
                .error((code, message) -> Log.e("latteCat","error"+code+"msg"+message))
                .build()
                .get();


        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new HomeDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-sort}", "动态"), new DynamicDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    /**
     * 默认显示第一个页面
     * @return
     */
    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return 0xff33b5e5;
    }
}
