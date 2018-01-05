package poc.orchestrationandchoreography.order.port.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import poc.orchestrationandchoreography.order.port.message.Message;
import poc.orchestrationandchoreography.order.port.message.MessageSender;

@Component
public class OrderCompletedAdapter implements JavaDelegate {
  
  @Autowired
  private MessageSender messageSender;  

  @Override
  public void execute(DelegateExecution context) throws Exception {
    String orderId = (String)context.getVariable("orderId"); 
    String traceId = context.getProcessBusinessKey();

    messageSender.send( //
        new Message<OrderCompletedEventPayload>( //
            "OrderCompletedEvent", //
            traceId, //
            new OrderCompletedEventPayload() //
              .setOrderId(orderId)));
  }

  

}
