package vttp2022.paf.assessment.eshop.models;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

// DO NOT CHANGE THIS CLASS
public class Order {

	private String orderId;
	private String deliveryId;
	private String name;
	private String address;
	private String email;
	private String status;
	private Date orderDate = new Date();
	private List<LineItem> lineItems = new LinkedList<>();

	public JsonObject toJson() {

        final String CREATED_BY_NAME = "Gay Horng Tze James"; 
		JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for(LineItem li: lineItems) {
            arrayBuilder.add(objectBuilder.add("item", li.getItem())
                                            .add("quantity", li.getQuantity())
											.build());
        }

        return Json.createObjectBuilder()
                    .add("orderId", getOrderId())
                    .add("name", getName())
                    .add("address", getAddress())               
                    .add("email", getEmail())
					.add("lineItems", arrayBuilder)  
					.add("createdBy", CREATED_BY_NAME)            
                    .build();
    }

	public Order createOrder(SqlRowSet rs, List<LineItem> lineItems) {
		Order order = new Order(); 
		order.setOrderId(rs.getString("order_id"));
		order.setName(rs.getString("name"));
		order.setAddress(rs.getString("address"));
		order.setEmail(rs.getString("email"));
		order.setOrderDate(rs.getDate("order_date"));
		order.setLineItems(lineItems);
		return order;
	}

	public Order() {
		
	}

	public Order(String orderId, String deliveryId, String name, String address, String email, String status,
			Date orderDate, List<LineItem> lineItems) {
		this.orderId = orderId;
		this.deliveryId = deliveryId;
		this.name = name;
		this.address = address;
		this.email = email;
		this.status = status;
		this.orderDate = orderDate;
		this.lineItems = lineItems;
	}

	public String getOrderId() { return this.orderId; }
	public void setOrderId(String orderId) { this.orderId = orderId; }

	public String getDeliveryId() { return this.deliveryId; }
	public void setDeliveryId(String deliveryId) { this.deliveryId = deliveryId; }

	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }

	public String getAddress() { return this.address; }
	public void setAddress(String address) { this.address = address; }

	public String getEmail() { return this.email; }
	public void setEmail(String email) { this.email = email; }

	public String getStatus() { return this.status; }
	public void setStatus(String status) { this.status = status; }

	public Date getOrderDate() { return this.orderDate; }
	public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

	public Customer getCustomer() { 
		Customer customer = new Customer();
		customer.setName(name);
		customer.setAddress(address);
		customer.setEmail(email);
		return customer;
	}
	public void setCustomer(Customer customer) {
		name = customer.getName();
		address = customer.getAddress();
		email = customer.getEmail();
	}

	public List<LineItem> getLineItems() { return this.lineItems; }
	public void setLineItems(List<LineItem> lineItems) { this.lineItems = lineItems; }
	public void addLineItem(LineItem lineItem) { this.lineItems.add(lineItem); }
}

