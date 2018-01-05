package poc.orchestrationandchoreography.monitor.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poc.orchestrationandchoreography.monitor.domain.PastEvent;
import poc.orchestrationandchoreography.monitor.port.persistence.LogRepository;

@RestController
public class MonitorRestController {
  
  @RequestMapping(path = "/event", method = GET)
  public Map<String, List<PastEvent>> getAllEvents() {
    return LogRepository.instance.getAllPastEvents();
  }

  @RequestMapping(path = "/event/{traceId}", method = GET)
  public List<PastEvent> getAllEvents(@PathVariable String traceId) {
    return LogRepository.instance.getAllPastEvents(traceId);
  }

}