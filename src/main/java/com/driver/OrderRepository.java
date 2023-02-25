package com.driver;

import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

@Repository
public class OrderRepository {
    HashMap<String, Order> orderHashMap = new HashMap<>();
    HashMap<String, DeliveryPartner> partnerHashMap = new HashMap<>();
    HashMap<String, List<String>> patnerOrderPairHashMap = new HashMap<>(); // key - partner id, value - order id
    HashMap<String, String> orderPartner = new HashMap<>();

    // 1
    public void addOrder(Order order){
        orderHashMap.put(order.getId(),order);
    }
    // 2
    public void addPartner(String partnerId){
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        partnerHashMap.put(partnerId, deliveryPartner);
    }
    // 3
    public void makePartnerOrderPair(String orderId, String partnerId){
        List<String> orderList = patnerOrderPairHashMap.getOrDefault(partnerId, new ArrayList<>());
        orderList.add(orderId);
        patnerOrderPairHashMap.put(partnerId, orderList);
        orderPartner.put(orderId, partnerId);
        DeliveryPartner deliveryPartner = partnerHashMap.get(partnerId);
        deliveryPartner.setNumberOfOrders(orderList.size());
    }
    // 4
    public Order findOrderById(String orderId){
        for(String id : orderHashMap.keySet()){
            if(id.equals(orderId)){
                return orderHashMap.get(orderId);
            }
        }
        return null;
    }
    // 5
    public DeliveryPartner findPartnerById(String partnerId){
        for(String id : partnerHashMap.keySet()){
            if(id.equals(partnerId)){
                return partnerHashMap.get(partnerId);
            }
        }
        return null;
    }
    // 6
    public Integer findOrderCountByPartnerId(String partnerId){
        int orders = patnerOrderPairHashMap.getOrDefault(partnerId, new ArrayList<>()).size();
        return orders;
    }
    // 7 Get List of all orders assigned to the given partnerId:
    public List<String> findOrdersByPartnerId(String partnerId){
        List<String> orders = patnerOrderPairHashMap.getOrDefault(partnerId, new ArrayList<>());
        return orders;
    }
    // 8 Get List of all orders in the system
    public List<String> findAllOrders() {
        return new ArrayList<>(orderHashMap.keySet());
    }
    // 9 Get count of orders which are not assigned to any partner:
    public Integer getCountOfUnassignedOrders(){
        // unpaired order = total order - paired order
        return orderHashMap.size() - orderPartner.size();
    }
    // 10 Get count of orders which are left undelivered by partnerId after given time
    public Integer findOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId){
        Integer remainingOrders = 0;
        List<String> orderList = patnerOrderPairHashMap.get(partnerId);
        String[] hourMinute = time.split(":");
        int hour = Integer.parseInt(hourMinute[0]);
        int minute = Integer.parseInt(hourMinute[1]);
        int deliveryTime = (hour*60)+minute;
        for(String orderId : orderList){
            Order order = orderHashMap.get(orderId);
            if(order.getDeliveryTime() > deliveryTime){
                remainingOrders++;
            }
        }
        return remainingOrders;
    }
    // 11 Get the time at which the last delivery is made by given partner
    public String findLastDeliveryTimeByPartnerId(String partnerId){

        List<String> orderList = patnerOrderPairHashMap.get(partnerId);
        int deliveryTime = 0;
        // find max delivery time from each order
        for(String order : orderList){
            Order order1 = orderHashMap.get(order);
            deliveryTime = Math.max(deliveryTime,order1.getDeliveryTime());
        }
        String time = convertTime(deliveryTime);
        return time;
    }
    // 12 Delete a partner and the corresponding orders should be unassigned
    public void deletePartnerById(String partnerId){
        List<String> orderList;
        if(patnerOrderPairHashMap.containsKey(partnerId)) {
            orderList = patnerOrderPairHashMap.get(partnerId);
            for (String order : orderList) {
                orderHashMap.remove(order);
                orderPartner.remove(order);
            }
            partnerHashMap.remove(partnerId);
            patnerOrderPairHashMap.remove(partnerId);
        }
    }
    // 13 Delete an order and the corresponding partner should be unassigned
    public void deleteOrderById(String orderId){
        // 1. remove from orderHashMap
        orderHashMap.remove(orderId);
        String partnerId = orderPartner.get(orderId);
        // 2. remove from pair hashmap
        List<String> orderList = patnerOrderPairHashMap.get(partnerId);
        ListIterator<String> itr = orderList.listIterator();
        // orderList.removeIf(order -> order.equals(orderId));
        while(itr.hasNext()){
            String order = itr.next();
            if(order.equals(orderId)){
                itr.remove();
            }
        }
        // 3. remove from orderPartner
        orderPartner.remove(orderId);
    }
    private String convertTime(int time){
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
