package com.driver;

public class Order {

    private String id;
    private String deliveryTime;
    private int deliveryTimeInt;

    public Order() {
    }

    public Order(String id, String deliveryTime) {
        this.id = id;
        this.deliveryTime = deliveryTime;
        // The deliveryTime has to converted from string to int and then stored in the attribute
        // deliveryTime  = HH*60 + MM
        String[] hourMinute = deliveryTime.split(":");
        int hour = Integer.parseInt(hourMinute[0]);
        int minute = Integer.parseInt(hourMinute[1]);
        this.deliveryTimeInt = (hour*60)+minute;
    }
    public String getId() {
        return id;
    }

    public String getDeliveryTime() {return deliveryTime;}
    public int getDeliveryTimeInt(){
        return deliveryTimeInt;
    }
}
