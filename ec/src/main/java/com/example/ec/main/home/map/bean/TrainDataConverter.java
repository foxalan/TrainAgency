package com.example.ec.main.home.map.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alan
 *         Date  2018/6/9.
 *         Function : 网络请求培训机构信息
 *         Issue :
 */

public class TrainDataConverter {

    /**
     * 请求得到附近的培训机构
     * @return
     */
    public List<TrainBean> convert(){

        List<TrainBean> trainBeanList = new ArrayList<>();

        //30.622694===114.359512
        TrainBean train1 = new TrainBean.Builder()
                .withLatitude(30.622594)
                .withLongitude(114.359412)
                .withTitle("软产")
                .build();

        TrainBean train2 = new TrainBean.Builder()
                .withLatitude(30.622794)
                .withLongitude(114.359612)
                .withTitle("软产")
                .build();

        trainBeanList.add(train1);
        trainBeanList.add(train2);

        return trainBeanList;

    }

}
