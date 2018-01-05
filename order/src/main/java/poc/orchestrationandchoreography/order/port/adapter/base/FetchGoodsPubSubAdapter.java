package poc.orchestrationandchoreography.order.port.adapter.base;

import org.camunda.bpm.engine.impl.pvm.delegate.ActivityExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import poc.orchestrationandchoreography.order.domain.Order;
import poc.orchestrationandchoreography.order.port.adapter.FetchGoodsCommandPayload;
import poc.orchestrationandchoreography.order.port.message.Message;
import poc.orchestrationandchoreography.order.port.message.MessageSender;
import poc.orchestrationandchoreography.order.port.persistence.OrderRepository;

/**
 * Alternative implementation if you prefer having send/receive in one single ServiceTask
 * which is often easier understood by "normal people"
 *
 */
@Component
public class FetchGoodsPubSubAdapter extends PublishSubscribeAdapter {
  
  @Autowired
  private MessageSender messageSender;

  @Autowired
  private OrderRepository orderRepository;

  @Override
  public void execute(ActivityExecution context) throws Exception {
    Order order = orderRepository.getOrder( //
        (String)context.getVariable("orderId")); 
    String traceId = context.getProcessBusinessKey();

    // publish
    messageSender.send(new Message<FetchGoodsCommandPayload>( //
            "FetchGoodsCommand", //
            traceId, //
            new FetchGoodsCommandPayload() //
              .setRefId(order.getId()) //
              .setItems(order.getItems())));
    
    addMessageSubscription(context, "GoodsFetchedEvent");
  }
  
}
