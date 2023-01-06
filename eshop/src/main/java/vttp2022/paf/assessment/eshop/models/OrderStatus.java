package vttp2022.paf.assessment.eshop.models;

// DO NOT CHANGE THIS CLASS
public class OrderStatus {

	public enum Status {pending, dispatched}

	private String orderId;
	private String deliveryId = "";
	private Status status;

	public String getOrderId() { return this.orderId; }
	public void setOrderId(String orderId) { this.orderId = orderId; }

	public String getDeliveryId() { return this.deliveryId; }
	public void setDeliveryId(String deliveryId) { this.deliveryId = deliveryId; }

	public Status getStatus() { return this.status; }
	public void setStatus(Status status) { this.status = status; }
    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }
	
}
