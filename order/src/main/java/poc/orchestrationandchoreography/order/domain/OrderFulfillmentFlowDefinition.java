package poc.orchestrationandchoreography.order.domain;

import javax.annotation.PostConstruct;

import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderFulfillmentFlowDefinition {

  @Autowired
  private ProcessEngine engine;
  
  @Autowired 
  private ApplicationContext applicationContext;
  
  @PostConstruct
  public void createFlow() {
  }
  
  public String exp(Class delegateClass) {
    String[] beanNames = applicationContext.getBeanNamesForType(delegateClass);
    if (beanNames.length>1) {
      throw new RuntimeException("More than one Spring bean found for type " + delegateClass);
    }
    return "#{" + beanNames[0] + "}";
  }
  
 }
