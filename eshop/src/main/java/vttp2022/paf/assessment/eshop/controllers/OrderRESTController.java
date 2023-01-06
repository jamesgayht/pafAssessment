package vttp2022.paf.assessment.eshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;
import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

import java.io.StringReader;
import java.util.Optional;

@RestController
@RequestMapping(path = "/http://paf.chuklee.com", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderRESTController {

    @Autowired
    private OrderRepository orderRepository;

    
    @PostMapping(path = "/dispatch/{orderId}")
    public ResponseEntity<String> dispatchOrder(@PathVariable String orderId) {

        Optional<Order> opt = orderRepository.findPreviousOrder(orderId); 
        Order order = new Order(); 

        if(opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body("Couldnt Find the OrderID."); 
        }
        else {
            order = opt.get(); 
            JsonObject results = order.toJson(); 
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(results.toString()); 
        }   
    }

}
