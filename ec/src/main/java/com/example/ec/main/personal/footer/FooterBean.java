package com.example.ec.main.personal.footer;

/**
 * @author alan
 *         Date  2018/7/9.
 *         Function :
 *         Issue :
 */

public class FooterBean {

   private int id;
   private int  heatTarget ;
   private String img;
   private int targetId ;
   private String time ;
   private boolean isDelete;

    public FooterBean(int id, int heatTarget, String img, int targetId, String time) {
        this.id = id;
        this.heatTarget = heatTarget;
        this.img = img;
        this.targetId = targetId;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeatTarget() {
        return heatTarget;
    }

    public void setHeatTarget(int heatTarget) {
        this.heatTarget = heatTarget;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
