package com.driver;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    public void addOrder(Order order){
        orderRepository.addOrder(order);
    }
    public void addPartner(String id){
        orderRepository.addPartner(id);
    }
    public void orderPartnerPair(String orderId, String partnerId){
        orderRepository.makePartnerOrderPair(orderId,partnerId);
    }
    public Order getOrderById(String id){
        return orderRepository.findOrderById(id);
    }
    public DeliveryPartner getPartnerById(String id){
        return orderRepository.findPartnerById(id);
    }
    public Integer getOrderCountByPartnerId(String partnerId){
        return orderRepository.findOrderCountByPartnerId(partnerId);
    }
    public List<String> getOrdersByPartnerId(String partnerId){
        return orderRepository.findOrdersByPartnerId(partnerId);
    }
    public List<String> getAllOrders(){
        return orderRepository.findAllOrders();
    }
    public Integer getCountOfUnassignedOrders(){
        return orderRepository.getCountOfUnassignedOrders();
    }
    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId){
        return orderRepository.findOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
    }
    public String getLastDeliveryTimeByPartnerId(String partnerId){
        return orderRepository.findLastDeliveryTimeByPartnerId(partnerId);
    }
    public void deletePartnerById(String partnerId){
        orderRepository.deletePartnerById(partnerId);
    }
    public void deleteOrderById(String orderId){
        orderRepository.deleteOrderById(orderId);
    }

}
