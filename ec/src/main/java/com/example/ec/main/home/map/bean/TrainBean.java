package com.example.ec.main.home.map.bean;

/**
 * @author alan
 *         Date  2018/6/9.
 *         Function : 培训机构位置信息
 *         Issue :
 */

public class TrainBean {

    private double latitude;
    private double longitude;
    private String title;

    public TrainBean(double latitude, double longitude, String title) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class Builder{

        private double latitude;
        private double longitude;
        private String title;

        public Builder withLatitude(double latitude){
            this.latitude = latitude;
            return this;
        }

        public Builder withLongitude(double longitude){
            this.longitude = longitude;
            return this;
        }

        public Builder withTitle(String title){
            this.title = title;
            return this;
        }

        public TrainBean build(){
            return new TrainBean(latitude,longitude,title);
        }
    }
}
