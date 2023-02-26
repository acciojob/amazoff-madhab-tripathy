package com.driver;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class OrderService {

    OrderRepository orderRepository = new OrderRepository();
    public String addOrder(Order order){
        return orderRepository.addOrder(order);
    }
    public String addPartner(String id){
        return orderRepository.addPartner(id);
    }
    public String addOrderPartnerPair(String orderId, String partnerId){
        return orderRepository.addOrderPartnerPair(orderId,partnerId);
    }
    public Order getOrderById(String id){
        return orderRepository.getOrderById(id);
    }
    public DeliveryPartner getPartnerById(String id){
        return orderRepository.getPartnerById(id);
    }
    public Integer getOrderCountByPartnerId(String partnerId){
        return orderRepository.getOrderCountByPartnerId(partnerId);
    }
    public List<String> getOrdersByPartnerId(String partnerId){
        return orderRepository.getOrdersByPartnerId(partnerId);
    }
    public List<String> getAllOrders(){
        return orderRepository.getAllOrders();
    }
    public Integer getCountOfUnassignedOrders(){
        return orderRepository.getCountOfUnassignedOrders();
    }
    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId){
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
    }
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        return orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }
    public String deletePartnerById(String partnerId){
        return orderRepository.deletePartnerById(partnerId);
    }
    public String deleteOrderById(String orderId){
        return orderRepository.deleteOrderById(orderId);
    }

}