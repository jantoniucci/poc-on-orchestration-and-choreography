package poc.orchestrationandchoreography.shipping.application;

import java.util.UUID;

import org.springframework.stereotype.Component;


@Component
public class ShippingService {
  public String createShipment(String pickId, String recipientName, String recipientAddress, String logisticsProvider) {
    System.out.println("Shipping to " + recipientName + "\n\n" + recipientAddress);
    
    return UUID.randomUUID().toString();
  }

}
