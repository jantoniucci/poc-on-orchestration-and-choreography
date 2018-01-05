package poc.orchestrationandchoreography.payment.port.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.el.FixedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import poc.orchestrationandchoreography.payment.port.message.Message;
import poc.orchestrationandchoreography.payment.port.message.MessageSender;

@Component
public class EmitEventAdapter implements JavaDelegate {

  @Autowired
  private MessageSender messageSender;

  private FixedValue eventName;

  @Override
  public void execute(DelegateExecution context) throws Exception {
    String traceId = context.getProcessBusinessKey();

    String eventNameString = null;
    if (eventName!=null && eventName.getValue(context)!=null) {
      eventNameString = (String) eventName.getValue(context);
    } else {
      eventNameString = context.getCurrentActivityId();
    }
    
    messageSender.send(new Message<String>( //
        eventNameString, //
        traceId, //
        null)); // no payload at the moment
  }

}
