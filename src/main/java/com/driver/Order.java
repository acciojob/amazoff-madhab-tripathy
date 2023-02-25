package com.driver;

public class Order {

    private String id;
    private int deliveryTime;
    private String deliveryTimeStr;

    public Order() {
    }

    public Order(String id, String dTimer) {
        this.id = id;
        this.deliveryTimeStr = dTimer;
//        this.deliveryTime = deliveryTime;
        // The deliveryTime has to converted from string to int and then stored in the attribute
        // deliveryTime  = HH*60 + MM
        String[] hourMinute = dTimer.split(":");
        int hour = Integer.parseInt(hourMinute[0]);
        int minute = Integer.parseInt(hourMinute[1]);
        this.deliveryTime = (hour*60)+minute;
    }
    public String getId() {
        return id;
    }

    public String getDeliveryTimeStr() {return deliveryTimeStr;}
    public int getDeliveryTime(){
        return deliveryTime;
    }
}
