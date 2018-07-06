package com.example.ec.main.personal.notice.detail;

import java.io.Serializable;

/**
 * @author alan
 *         Date  2018/7/4.
 *         Function :
 *         Issue :
 */

public class NoticeBean implements Serializable{

    private int id;
    private String imgUrl;
    private String name;
    private String phone;
    private boolean isNotice;

    public NoticeBean(int id, String imgUrl, String name, String phone, boolean isNotice) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.name = name;
        this.phone = phone;
        this.isNotice = isNotice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isNotice() {
        return isNotice;
    }

    public void setNotice(boolean notice) {
        isNotice = notice;
    }
}
