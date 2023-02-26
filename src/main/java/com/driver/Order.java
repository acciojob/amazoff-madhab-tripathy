package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id=id;
        int time =Integer.parseInt(deliveryTime.substring(0,2))*60+Integer.parseInt(deliveryTime.substring(3));
        this.deliveryTime=time;
    }
    public String getId() {
        return id;
    }
    public int getDeliveryTime() {return deliveryTime;}
    public static String convertTime(int time){
        int hr = time / 60;
        String hour = "";
        if (hr < 10) {
            hour = "0" + String.valueOf(hr);
        } else {
            hour = String.valueOf(hr);
        }

        int min = time % 60;
        String minute = "";
        if (min < 10) {
            minute = "0" + String.valueOf(min);
        } else {
            minute = String.valueOf(min);
        }

        String deleveryTime = hour + ":" + minute;
        return deleveryTime;
    }
}
