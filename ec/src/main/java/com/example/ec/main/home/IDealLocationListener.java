package com.example.ec.main.home;



import com.baidu.location.BDLocation;

/**
 * @author alan
 *         Date  2018/6/9.
 *         Function :处理定位信息
 *         Issue :
 */

public interface IDealLocationListener {
    /**
     * 定位
     */
    void Location(BDLocation location);

}
