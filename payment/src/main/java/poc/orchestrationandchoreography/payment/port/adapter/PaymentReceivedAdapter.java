package poc.orchestrationandchoreography.payment.port.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import poc.orchestrationandchoreography.payment.port.message.Message;
import poc.orchestrationandchoreography.payment.port.message.MessageSender;

@Component
public class PaymentReceivedAdapter implements JavaDelegate {

  @Autowired
  private MessageSender messageSender;

  @Override
  public void execute(DelegateExecution context) throws Exception {
    String refId = (String) context.getVariable("refId");
    String traceId = context.getProcessBusinessKey();

    messageSender.send( //
        new Message<PaymentReceivedEventPayload>( //
            "PaymentReceivedEvent", //
            traceId, //
            new PaymentReceivedEventPayload() //
                .setRefId(refId)));
  }

}
