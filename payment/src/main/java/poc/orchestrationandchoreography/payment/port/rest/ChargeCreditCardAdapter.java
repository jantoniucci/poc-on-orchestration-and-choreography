package poc.orchestrationandchoreography.payment.port.rest;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConfigurationProperties
public class ChargeCreditCardAdapter implements JavaDelegate {

  @Autowired
  private RestTemplate rest;

  private String stripeChargeUrl = "http://localhost:8099/charge";

  public void execute(DelegateExecution ctx) throws Exception {
    CreateChargeRequest request = new CreateChargeRequest();
    request.amount = (int) ctx.getVariable("remainingAmount");

    CreateChargeResponse response = rest.postForObject( //
        stripeChargeUrl, //
        request, //
        CreateChargeResponse.class);   
    
    if (response.errorCode!=null) {
      ctx.setVariable("errorCode", response.errorCode);
      throw new BpmnError("Error_PaymentError");
    }

    ctx.setVariable("paymentTransactionId", response.transactionId);
  }
  
  public static class CreateChargeRequest {
    public int amount;
  }

  public static class CreateChargeResponse {
    public String transactionId;
    public String errorCode;
  }

  public RestTemplate getRest() {
    return rest;
  }

  public void setRest(RestTemplate rest) {
    this.rest = rest;
  }

  public String getStripeChargeUrl() {
    return stripeChargeUrl;
  }

  public void setStripeChargeUrl(String stripeChargeUrl) {
    this.stripeChargeUrl = stripeChargeUrl;
  }

}
