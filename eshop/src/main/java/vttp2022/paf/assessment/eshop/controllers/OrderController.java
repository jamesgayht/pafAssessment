package vttp2022.paf.assessment.eshop.controllers;

import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;
import vttp2022.paf.assessment.eshop.services.OrderService;

@Controller
@RequestMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    private CustomerRepository customerRepository; 

    @Autowired
    private OrderRepository orderRepository; 

    @Autowired
    private OrderService orderService; 

    @PostMapping(path = "/api/order")
    public ResponseEntity<String> insertLineItemsIntoOrder(@RequestBody String body) throws Exception {

        JsonReader jsonReader = Json.createReader(new StringReader(body)); 
        JsonObject jsonObject = jsonReader.readObject(); 
        String name = jsonObject.getString("name"); 
        JsonArray lineItemsJson = jsonObject.getJsonArray("lineItems");
        List<LineItem> lineItems = new LinkedList<>(); 

        Optional<Customer> opt = customerRepository.findCustomerByName(name); 
        Customer customer = new Customer(); 
        String jsonCustomerError = "{\"error\": \"Customer %s not found\"}".formatted(name);

        if(opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(jsonCustomerError); 
        }
        else {
            customer = opt.get();
            
            lineItems = lineItemsJson.stream()
                            .map(v -> (JsonObject)v)
                            .map(v -> LineItem.fromCache(v))
                            .toList();

            String orderId = UUID.randomUUID().toString().substring(0, 8); 
            Date orderDate = new Date(); 
            System.out.println("ORDER DATE >>> " + orderDate.toString());

            Order order = new Order();
            order.setOrderId(orderId);
            order.setCustomer(customer);
            order.setLineItems(lineItems);
            order.setOrderDate(orderDate); 
            
            try {
                orderService.createNewOrder(order);
            } catch (Exception e) {
                String jsonOrderError = "{\"error\": \"%s\"}".formatted(e.getMessage());

                return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(jsonOrderError); 
            }

            
            String completed = "{\"orderId\": \"%s\"}".formatted(order.getOrderId());

            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(completed); 
        }
    }

    public ResponseEntity<String> insertOrderStatus(@RequestBody String body) {
        JsonReader jsonReader = Json.createReader(new StringReader(body)); 
        JsonObject jsonObject = jsonReader.readObject(); 
        String orderId = jsonObject.getString("orderId"); 
        String deliveryId = jsonObject.getString("deliveryId"); 
        String status = jsonObject.getString("status");

        
        return null; 
    }


}
