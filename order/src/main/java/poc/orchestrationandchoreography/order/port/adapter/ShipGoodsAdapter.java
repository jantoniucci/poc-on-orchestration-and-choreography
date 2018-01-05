package poc.orchestrationandchoreography.order.port.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import poc.orchestrationandchoreography.order.domain.Order;
import poc.orchestrationandchoreography.order.port.message.Message;
import poc.orchestrationandchoreography.order.port.message.MessageSender;
import poc.orchestrationandchoreography.order.port.persistence.OrderRepository;

@Component
public class ShipGoodsAdapter implements JavaDelegate {
  
  @Autowired
  private MessageSender messageSender;  

  @Autowired
  private OrderRepository orderRepository;  

  @Override
  public void execute(DelegateExecution context) throws Exception {
    Order order = orderRepository.getOrder( //
        (String)context.getVariable("orderId")); 
    String pickId = (String)context.getVariable("pickId"); // TODO read from step before!
    String traceId = context.getProcessBusinessKey();

    messageSender.send(new Message<ShipGoodsCommandPayload>( //
            "ShipGoodsCommand", //
            traceId, //
            new ShipGoodsCommandPayload() //
              .setRefId(order.getId())
              .setPickId(pickId) //
              .setRecipientName(order.getCustomer().getName()) //
              .setRecipientAddress(order.getCustomer().getAddress()))); 
  }  

}
