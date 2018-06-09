package com.example.ec.main.home.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author alan
 *         Date  2018/6/9.
 *         Function : 获取搜索记录
 *         Issue :
 */

public class SearchDataConverter {


    public List<String> convert(){
        List<String> dataList = new ArrayList<>();


        String[] mVals = new String[]{"Java", "Android", "iOS", "Python",
                "Mac OS", "PHP", "JavaScript", "Objective-C",
                "Groovy", "Pascal", "Ruby", "Go", "Swift"};

        dataList.addAll(Arrays.asList(mVals));

        return dataList;
    }

}
