package poc.orchestrationandchoreography.order.port.adapter;

public class OrderCompletedEventPayload {
  
  private String orderId;

  public String getOrderId() {
    return orderId;
  }

  public OrderCompletedEventPayload setOrderId(String orderId) {
    this.orderId = orderId;
    return this;
  }
}
