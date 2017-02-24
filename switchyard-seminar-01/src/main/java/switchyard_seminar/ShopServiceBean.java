package switchyard_seminar;

import javax.inject.Inject;

import org.switchyard.Context;
import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;

import switchyard_seminar.accounting.AccountingService;
import switchyard_seminar.model.Order;
import switchyard_seminar.model.OrderAck;
import switchyard_seminar.model.OrderStatus;

@Service(ShopService.class)
public class ShopServiceBean implements ShopService {

	@Inject
	Context context;

	@Inject
	@Reference
	AccountingService accountingService;

	@Inject
	@Reference
	OrderStatusService orderStatusService;

	@Override
	public OrderAck submitOrder(Order order) {
		// Initialize order status
		OrderStatus orderStatus = new OrderStatus(order);
		orderStatusService.save(orderStatus);

		// Return order acknowledgement
		OrderAck orderAck = new OrderAck();
		orderAck.setOrderId(order.getId());
		return orderAck;
	}

}
