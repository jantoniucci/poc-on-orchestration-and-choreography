package poc.orchestrationandchoreography.checkout.port.web;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import poc.orchestrationandchoreography.checkout.domain.Customer;
import poc.orchestrationandchoreography.checkout.domain.Order;
import poc.orchestrationandchoreography.checkout.port.message.Message;
import poc.orchestrationandchoreography.checkout.port.message.MessageSender;

@RestController
public class ShopRestController {
  
  @Autowired
  private MessageSender messageSender;
  
  @RequestMapping(path = "/api/cart/order", method = PUT)
  public String placeOrder(@RequestParam(value = "customerId") String customerId) {
    
    Order order = new Order();
    order.addItem("candies", 5);
    order.addItem("lollypops", 10);
    
    order.setCustomer(new Customer("Javier", "c/Esteban Palacios\n28043 Madrid\nSpain"));
    
    Message<Order> message = new Message<Order>("OrderPlacedEvent", order);
    messageSender.send(message);
        
    return "{\"traceId\": \"" + message.getTraceId() + "\"}";
  }

}