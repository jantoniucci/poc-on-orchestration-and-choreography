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
public class RetrievePaymentAdapter implements JavaDelegate {
  
  @Autowired
  private MessageSender messageSender;  

  @Autowired
  private OrderRepository orderRepository;  

  @Override
  public void execute(DelegateExecution context) throws Exception {
    Order order = orderRepository.getOrder( //
        (String)context.getVariable("orderId")); 
    String traceId = context.getProcessBusinessKey(); 
    
    messageSender.send( //
        new Message<RetrievePaymentCommandPayload>( //
            "RetrievePaymentCommand", //
            traceId, //
            new RetrievePaymentCommandPayload() //
              .setRefId(order.getId()) //
              .setAmount(order.getTotalSum())));
  }

}
