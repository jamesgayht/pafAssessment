package vttp2022.paf.assessment.eshop.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional(rollbackFor = OrderException.class)
    public void createNewOrder(Order order) throws OrderException{

        orderRepository.insertPurchaseOrder(order);

        String orderId = order.getOrderId(); 

        orderRepository.addLineItem(order.getLineItems(), orderId);
    }
    
}
