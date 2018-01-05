package poc.orchestrationandchoreography.inventory.application;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import poc.orchestrationandchoreography.inventory.domain.Item;
import poc.orchestrationandchoreography.inventory.domain.PickOrder;

@Component
public class InventoryService {
  
  public boolean reserveGoods(List<Item> items, String reason, String refId, LocalDateTime expirationDate) {
    // TODO: Implement
    return true;
  }

  public String pickItems(List<Item> items, String reason, String refId) {
    PickOrder pickOrder = new PickOrder().setItems(items);
    System.out.println("# Items picked: " + pickOrder);      
    return pickOrder.getPickId();
  }

  public void topUpInventory(String articleId, int amount) {
    // TODO: Implement
  }

}
