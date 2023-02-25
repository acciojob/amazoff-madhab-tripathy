package com.driver;

public class Order {

    private String id;
    private int deliveryTime;
    public Order(String id, String deliveryTime) {
        this.id = id;
        // The deliveryTime has to converted from string to int and then stored in the attribute
        // deliveryTime  = HH*60 + MM
        String[] hourMinute = deliveryTime.split(":");
        int hour = Integer.parseInt(hourMinute[0]);
        int minute = Integer.parseInt(hourMinute[1]);
        this.deliveryTime = (hour*60)+minute;
    }
    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
