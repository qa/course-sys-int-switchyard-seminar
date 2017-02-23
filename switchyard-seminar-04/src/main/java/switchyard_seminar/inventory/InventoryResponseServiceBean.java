package switchyard_seminar.inventory;

import javax.inject.Inject;

import org.switchyard.Context;
import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;

import switchyard_seminar.OrderStatusService;
import switchyard_seminar.model.OrderItemStatus;
import switchyard_seminar.model.OrderStatus;

@Service(InventoryResponseService.class)
public class InventoryResponseServiceBean implements InventoryResponseService {

	@Inject
	Context context;

	@Inject
	@Reference
	OrderStatusService orderStatusService;

	@Override
	public void process(InventoryResponse inventoryResponse) {
		long id = Long.valueOf(context.getPropertyValue("orderId").toString());
		OrderStatus orderStatus = orderStatusService.find(id);
		for (OrderItemStatus orderItemStatus : inventoryResponse.getOrderItemStatuses()) {
			if (orderItemStatus.getAmountReserved() == 0) {
				orderStatus.setInventoryStatus("FAIL");
				break;
			}
		}
		orderStatus.setInventoryStatus("OK");
		orderStatusService.save(orderStatus);
	}

}
